package com.ljm.chat.config;


import com.ljm.chat.interceptor.SessionHandleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * @author Dominick Li
 * @createTime 2020/3/10 15:44
 * @description 配置拦截器
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器,拦截前台页面
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionHandleInterceptor()).addPathPatterns("/user/index");
    }

}
