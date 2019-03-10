package com.light.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: Edison * @Date: 2019/1/2 22:42 * @Description: *
 */
//import com.theone.project.blogcommon.support.StaticPagePathFinder;

import java.io.IOException;

@Configuration
public class ViewConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private StaticPagePathFinder staticPagePathFinder;

    /*
    addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
    registry.addViewController("请求路径").setViewName("请求页面文件路径")
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        try{
            for(StaticPagePathFinder.PagePaths pagePaths :staticPagePathFinder.findPath()){
                String urlPath = pagePaths.getUrlPath();
                System.out.println(pagePaths.getUrlPath());
                System.out.println(pagePaths.getFilePath());
                registry.addViewController(urlPath).setViewName("twts/"+pagePaths.getFilePath());
                if(!urlPath.isEmpty()){
                    registry.addViewController(urlPath).setViewName("twts/"+pagePaths.getFilePath());
                }
            }
            //registry.addViewController( "/" ).setViewName( "forward:/login.html" );
        }catch(IOException e){
            throw new RuntimeException("Unable to locate static pages:"+e.getMessage(),e);
        }
    }
}

