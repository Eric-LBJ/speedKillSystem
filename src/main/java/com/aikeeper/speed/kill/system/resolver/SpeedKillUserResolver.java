package com.aikeeper.speed.kill.system.resolver;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.component.SpeedKillUserComponent;
import com.aikeeper.speed.kill.system.context.UserContext;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/27 14:45
 * @Version V1.0
 **/
@Component
public class SpeedKillUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == SpeedKillUserVO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        return UserContext.getUser();
    }

}
