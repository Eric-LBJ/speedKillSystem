package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.GoodsInfoService;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;
import com.aikeeper.speed.kill.system.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Result<SpeedKillUserDTO> getUserInfo(SpeedKillUserDTO speedKillUserDTO) {
        return Result.success(speedKillUserDTO);
    }

}
