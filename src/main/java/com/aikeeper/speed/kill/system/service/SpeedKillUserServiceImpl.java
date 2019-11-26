package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.SpeedKillUserService;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.SpeedKillUserKey;
import com.aikeeper.speed.kill.system.dal.RedisDao;
import com.aikeeper.speed.kill.system.dal.SpeedKillUserMapper;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillUser;
import com.aikeeper.speed.kill.system.domain.vo.LoginVo;
import com.aikeeper.speed.kill.system.exception.GlobalException;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.utils.KeyFactory;
import com.aikeeper.speed.kill.system.utils.Md5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
@Service("speedSkillUserService")
public class SpeedKillUserServiceImpl implements SpeedKillUserService {

    @Resource
    private SpeedKillUserMapper speedKillUserMapper;

    @Resource
    private RedisDao redisDao;

    @Override
    public SpeedKillUserDTO getSpeedKillUserById(Long id) {
        return infoToDto(speedKillUserMapper.getSpeedKillUserById(id));
    }

    @Override
    public Boolean userLogin(HttpServletResponse response, LoginVo loginVo) {

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
        String token = KeyFactory.genLoginToken(20);
        addCookie(response, token, speedKillUser);

        return Boolean.TRUE;
    }

    @Override
    public SpeedKillUserDTO getSpeedKillUserByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return infoToDto(redisDao.get(SpeedKillUserKey.token, token, SpeedKillUser.class));
    }

    private void addCookie(HttpServletResponse response, String token, SpeedKillUser speedKillUser) {
        redisDao.set(SpeedKillUserKey.token, token, speedKillUser);
        Cookie cookie = new Cookie(Constans.COOKI_NAME_TOKEN, token);
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
