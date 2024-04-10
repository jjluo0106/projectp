//package com.heima.interceptor;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override //目標資源方法運行前運行， true: 放行 / false: 不放行
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle(放行)...");
//        return true;
////        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//
//    @Override // 目標資源方法運行後運行
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle...");
//        //        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override // 視圖渲染完畢後運行，最後運行
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//        System.out.println("afterCompletion...");
////        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
