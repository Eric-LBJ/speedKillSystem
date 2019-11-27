package com.aikeeper.speed.kill.system.config;

import com.aikeeper.speed.kill.system.resolver.SpeedKillUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/27 14:10
 * @Version V1.0
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Resource
    private SpeedKillUserResolver speedKillUserResolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(speedKillUserResolver);
    }
}
