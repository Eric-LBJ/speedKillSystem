package com.aikeeper.speed.kill.system.config;

import com.aikeeper.speed.kill.system.resolver.SpeedKillUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(speedKillUserResolver);
    }

    /**
     * 这里有点踩坑了，springboot2.0中的WebMvcConfigurerAdapter已经被弃用了，所以我选择用WebMvcConfigurationSupport来添加参数解析器
     * 但是config继承了WebMvcConfigurationSupport之后默认会加载不到静态资源，所以需要加资源处理器
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }

}
