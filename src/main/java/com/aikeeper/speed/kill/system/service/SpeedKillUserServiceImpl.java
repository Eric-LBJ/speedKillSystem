package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.component.SpeedKillUserComponent;
import com.aikeeper.speed.kill.system.api.SpeedKillUserService;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.LoginVo;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
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
    private SpeedKillUserComponent speedKillUserComponent;

    @Override
    public SpeedKillUserVO getSpeedKillUserById(Long id) {
        return dtoToVo(speedKillUserComponent.getSpeedKillUserById(id));
    }

    @Override
    public Boolean userLogin(HttpServletResponse response, LoginVo loginVo) {
       return speedKillUserComponent.userLogin(response, loginVo);
    }

    @Override
    public SpeedKillUserVO getSpeedKillUserByToken(HttpServletResponse httpServletResponse, String token) {
        return dtoToVo(speedKillUserComponent.getSpeedKillUserByToken(httpServletResponse, token));
    }

    private SpeedKillUserVO dtoToVo(SpeedKillUserDTO speedKillUserDTO) {
        SpeedKillUserVO speedKillUserVO = new SpeedKillUserVO();
        if (!ObjectUtils.isEmpty(speedKillUserDTO)) {
            BeanUtils.copyProperties(speedKillUserDTO, speedKillUserVO);
        }
        return speedKillUserVO;
    }

}
