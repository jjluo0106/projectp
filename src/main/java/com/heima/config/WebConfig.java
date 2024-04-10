//package com.heima.config;
//import com.heima.interceptor.LoginInterceptor;
//import org.dom4j.io.SAXReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration //配置類
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/*");
//    }
//    @Scope("prototype")
//    @Bean //將方法返回直接給IOC容器管理,成為IOC容器的bean對象
//    public SAXReader reader(){
//        return new SAXReader();
//    }
//}
