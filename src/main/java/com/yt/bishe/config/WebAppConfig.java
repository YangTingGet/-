package com.yt.bishe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(getInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns( "/**/*.top-part", "/**/*.register", "/user/doLogin","/user/do_checkUserName","/user/doRegist","/index","/**/*.html",  "/js/**",  "/css/**", "/fonts/**", "/images/**", "/screenshots/**");
    }

    public HandlerInterceptor getInterceptor() {
        return new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                try {
                    //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
                    String userName = (String) request.getSession().getAttribute("userName");
                    if (userName != null) {
                        return true;
                    }
                    System.out.println("拦截成功");
                    request.setAttribute("failMsg", "未登录或登录过时，请登录");
                    response.sendRedirect(request.getContextPath()+"/index");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };


    }
}
