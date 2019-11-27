package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.SpeedKillUserService;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/to_list")
    public String toList(Model model, SpeedKillUserDTO speedKillUserDTO) {
        model.addAttribute("user", dtoToVo(speedKillUserDTO));
        return "goods_list";
    }

    private SpeedKillUserVO dtoToVo(SpeedKillUserDTO speedKillUserDTO) {
        SpeedKillUserVO speedKillUserVO = new SpeedKillUserVO();
        if (!ObjectUtils.isEmpty(speedKillUserDTO)) {
            BeanUtils.copyProperties(speedKillUserDTO, speedKillUserVO);
        }
        return speedKillUserVO;
    }

}
