package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.SpeedKillUserComponent;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.SpeedKillUserKey;
import com.aikeeper.speed.kill.system.dal.RedisDao;
import com.aikeeper.speed.kill.system.dal.SpeedKillUserMapper;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillUser;
import com.aikeeper.speed.kill.system.domain.vo.LoginVO;
import com.aikeeper.speed.kill.system.exception.GlobalException;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.utils.IdGeneratorUtils;
import com.aikeeper.speed.kill.system.utils.Md5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 15:09
 * @Version V1.0
 **/
@Component
public class SpeedKillUserComponentImpl implements SpeedKillUserComponent {

    @Resource
    private SpeedKillUserMapper speedKillUserMapper;

    @Resource
    private RedisDao redisDao;

    @Override
    public SpeedKillUserDTO getSpeedKillUserById(Long id) {
        return infoToDto(speedKillUserMapper.getSpeedKillUserById(id));
    }

    @Override
    public String userLogin(HttpServletResponse response, LoginVO loginVo) {

        SpeedKillUser speedKillUser = speedKillUserMapper.getSpeedKillUserById(Long.parseLong(loginVo.getMobile()));
        /**
         * 手机号是否存在校验
         */
        if (ObjectUtils.isEmpty(speedKillUser) || ObjectUtils.isEmpty(speedKillUser.getId())) {
            throw new GlobalException(CodeMessage.MOBILE_NOT_EXISTS);
        }
        /**
         * 密码校验
         */
        String password = Md5Utils.formPassToDbPassword(loginVo.getPassword(), speedKillUser.getSalt());
        if (!speedKillUser.getPassword().equals(password)) {
            throw new GlobalException(CodeMessage.PASSWORD_ERROR);
        }

        /**设置cookie*/
        String token = IdGeneratorUtils.simpleUUID();
        addCookie(response, token, speedKillUser);

        return token;
    }

    @Override
    public SpeedKillUserDTO getSpeedKillUserByToken(HttpServletResponse httpServletResponse, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SpeedKillUser speedKillUser = redisDao.get(SpeedKillUserKey.token, token, SpeedKillUser.class);
        if (!ObjectUtils.isEmpty(speedKillUser)) {
            addCookie(httpServletResponse, token, speedKillUser);
        }
        return infoToDto(speedKillUser);
    }

    private void addCookie(HttpServletResponse response, String token, SpeedKillUser speedKillUser) {
        redisDao.set(SpeedKillUserKey.token, token, speedKillUser);
        Cookie cookie = new Cookie(Constans.COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(SpeedKillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private SpeedKillUserDTO infoToDto(SpeedKillUser speedKillUser) {
        SpeedKillUserDTO speedKillUserDTO = new SpeedKillUserDTO();
        if (!ObjectUtils.isEmpty(speedKillUser)) {
            BeanUtils.copyProperties(speedKillUser, speedKillUserDTO);
        }
        return speedKillUserDTO;
    }

}
