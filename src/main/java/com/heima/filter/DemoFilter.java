//package com.heima.filter;
//
//import com.heima.pojo.Result;
//import com.heima.util.JwtUtils;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONObject;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//@Slf4j
////@WebFilter(urlPatterns = "/*")
//public class DemoFilter implements Filter {
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("过滤器初始化:");
//
//
//        Filter.super.init(filterConfig);
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req =(HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//
//        String url = req.getRequestURL().toString();
//        log.info("進入過濾器...");
//        if(url.contains("login")){
//            log.info("登入頁面，放行");
//            filterChain.doFilter(servletRequest,servletResponse);
//        }else {
//            String token = req.getHeader("token");
//            log.info("token : " + token);
//            if(!StringUtils.hasLength(token)){
//                log.info("未登入");
//                Result error = Result.error("NOT_LOGIN");
//                JSONObject jsonObject = new JSONObject(error);
//                res.getWriter().write(jsonObject.toString());
//                return;
//            }
//            try {
//                JwtUtils.parseJWT(token);
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.info("解析令牌失敗，返回未登入錯誤訊息");
//                Result error = Result.error("NOT_LOGIN");
//                JSONObject jsonObject = new JSONObject(error);
//                res.getWriter().write(jsonObject.toString());
//                return;
//            }
//            log.info("令牌合法，直接放行");
//            filterChain.doFilter(req,res);
//        }
//        log.info("過濾後...");
//    }
//    @Override
//    public void destroy() {
//        log.info("销毁");
//        Filter.super.destroy();
//    }
//}
