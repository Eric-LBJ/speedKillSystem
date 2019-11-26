package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 15:07
 * @Version V1.0
 **/
public interface SpeedKillUserService {

    /**
     * 根据手机获取用户信息
     *
     * @param id
     * @return
     */
    SpeedKillUserDTO getSpeedKillUserById(Long id);

    /**
     * 用户登录
     *
     * @param response
     * @param loginVo
     * @return
     */
    Boolean userLogin(HttpServletResponse response, LoginVo loginVo);

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    SpeedKillUserDTO getSpeedKillUserByToken(String token);
}
