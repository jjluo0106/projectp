//package com.heima.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//@Slf4j
////預設全部攔截
//@WebFilter("/test")
//public class ObservationEfficiencyFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//
//        long start = System.currentTimeMillis();
//        filterChain.doFilter(servletRequest,servletResponse);
//        long end = System.currentTimeMillis();
//        StringBuffer url = req.getRequestURL();
//        log.info("處理請求url : {} 耗時 : {}", url, end-start);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
