package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.RedisService;
import com.aikeeper.speed.kill.system.api.SpeedKillUserService;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.SpeedKillUserKey;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private RedisService redisService;

    @RequestMapping("/to_list")
    public String toList(Model model,
                         @CookieValue(value = Constans.COOKI_NAME_TOKEN, required = false) String cookieToken,
                         @RequestParam(value = Constans.COOKI_NAME_TOKEN, required = false) String paramToken) {
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        SpeedKillUser speedKillUser = redisService.get(SpeedKillUserKey.token, token, SpeedKillUser.class);
        model.addAttribute("user", speedKillUser);
        return "goods_list";
    }

}
