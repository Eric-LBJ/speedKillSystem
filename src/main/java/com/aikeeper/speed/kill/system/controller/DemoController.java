package com.aikeeper.speed.kill.system.controller;

import com.aikeeper.speed.kill.system.api.RedisService;
import com.aikeeper.speed.kill.system.api.UserService;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.UserKey;
import com.aikeeper.speed.kill.system.domain.info.User;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/22 15:11
 * @Version V1.0
 **/
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;


    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return "hello world";
    }

    @RequestMapping("/success")
    @ResponseBody
    public Result<String> success() {
        return Result.success("hello world");
    }

    @RequestMapping("/server/error")
    @ResponseBody
    public Result<String> serverError() {
        return Result.error(CodeMessage.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleafTest(Model model) {
        model.addAttribute("name", "Mr.zhang");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbTest() {
        User user = userService.getUserById((long) 1);
        return Result.success(user);
    }

    @RequestMapping("/db/transactional")
    @ResponseBody
    public Result<Boolean> transactionalTest() {
        Boolean result = userService.insertUser();
        return Result.success(result);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGetTest() {
        User user = redisService.get(UserKey.getById, "01", User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSetTest() {
        User user = new User((long) 1, "Mr.zhang");
        return Result.success(redisService.set(UserKey.getById, "01", user));
    }

}
