package com.light.config;

import com.light.springboot.filter.BaseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;
import java.io.IOException;

/**
 * @Author: Edison * @Date: 2019/1/2 22:42 * @Description: *
 */

// 在SpringBoot2.0及Spring 5.0 WebMvcConfigurerAdapter已被废弃
// 解决方案目前推荐下列两种:
// 1. 直接实现 WebMvcConfigurer 官方推荐
// 2. 直接继承WebMvcConfigurationSupport
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(BaseFilter.class);

    @Value("${server.staticPathPort:#{null}}")
    private String staticPort;

    @Autowired
    private StaticPagePathFinder staticPagePathFinder;

    /**
     *
     * 添加类型转换
     * @param registry
     * */
    @Override
    public void addFormatters(FormatterRegistry registry) {
    }

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    public void addInterceptors(InterceptorRegistry registry) {

    }
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("static port is " + staticPort);
        // 过滤swagger resource
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").
                addResourceLocations("http://localhost"+":" + staticPort);

    }

    /**
     * * addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
    * registry.addViewController("请求路径").setViewName("请求页面文件路径")
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        try{
            for(StaticPagePathFinder.PagePaths pagePaths :staticPagePathFinder.findPath()){
                String urlPath = pagePaths.getUrlPath();
                if(!urlPath.isEmpty()){
                    registry.addViewController(urlPath).setViewName(pagePaths.getFilePath() + ".html");
                }
            }
        }catch(IOException e){
            throw new RuntimeException("Unable to locate static pages:"+e.getMessage(),e);
        }
    }
}

