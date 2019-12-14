package com.aikeeper.speed.kill.system.annotation.filter;

import com.aikeeper.speed.kill.system.annotation.AccessLimit;
import com.aikeeper.speed.kill.system.api.RedisService;
import com.aikeeper.speed.kill.system.api.SpeedKillUserService;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.AccessKey;
import com.aikeeper.speed.kill.system.context.UserContext;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.result.Result;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/14 16:17
 * @Version V1.0
 **/
@Service("accessInterceptor")
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private SpeedKillUserService speedKillUserService;

    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        SpeedKillUserVO user = getUser(request, response);
        UserContext.setUser(user);

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if (ObjectUtils.isEmpty(accessLimit)) {
                return Boolean.TRUE;
            }
            Integer seconds = accessLimit.seconds();
            Integer maxCount = accessLimit.maxCount();
            Boolean needLogin = accessLimit.needLogin();
            /**
             * 校验是否需要登录
             */
            if (needLogin && ObjectUtils.isEmpty(user)) {
                render(response, CodeMessage.SESSION_ERROR);
                return Boolean.FALSE;
            }

            /**
             * 限流操作
             */
            String key = request.getRequestURI() + "_" + user.getId();
            Integer accessCount = redisService.get(AccessKey.createAccessKey(seconds), key, Integer.class);
            if (ObjectUtils.isEmpty(accessCount)) {
                redisService.set(AccessKey.createAccessKey(seconds), key, 1);
            } else if (accessCount < maxCount) {
                redisService.incr(AccessKey.createAccessKey(seconds), key);
            } else {
                render(response, CodeMessage.REQUEST_LIMIT_REACHED);
                return Boolean.FALSE;
            }
        }

        return super.preHandle(request, response, handler);
    }

    private SpeedKillUserVO getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(Constans.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, Constans.COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        return speedKillUserService.getSpeedKillUserByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookiName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void render(HttpServletResponse response, CodeMessage codeMessage) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(Result.error(codeMessage));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
