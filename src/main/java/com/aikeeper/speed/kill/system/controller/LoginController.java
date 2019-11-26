package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.SpeedKillUserService;
import com.aikeeper.speed.kill.system.domain.vo.LoginVo;
import com.aikeeper.speed.kill.system.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/22 15:11
 * @Version V1.0
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private SpeedKillUserService speedKillUserService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        return Result.success(speedKillUserService.userLogin(response, loginVo));
    }

}
