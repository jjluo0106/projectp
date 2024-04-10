//package com.chuyang.aop;
//
//import com.chuyang.mapper.OperateLogMapper;
//import com.chuyang.pojo.OperateLog;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Slf4j
//@Component
//@Aspect //切面類
//public class LogAspect {
//    @Autowired
//    OperateLogMapper operateLogMapper;
//
//    @Around("@annotation(com.chuyang.anno.CostumeAnno)")
//    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
//
//
//        //調用原始目標方法運行
//        long start = System.currentTimeMillis();
//        Object result = joinPoint.proceed();
//        long end = System.currentTimeMillis();
//
//        //操作人ID
//        Integer operateUser = 666;
//        //操作時間
//        LocalDateTime operateTime = LocalDateTime.now();
//        //操作類名
//        String className = joinPoint.getTarget().getClass().getName();
//        //操作方法名
//        String methodName = joinPoint.getSignature().getName();
//        //操作方法參數
//        Object[] args = joinPoint.getArgs();
//        String methodParams = Arrays.toString(args);
//        //操作方法返回值
//        String returnValue = new JSONObject(result).toString();
//        //操作耗時
//        long costTime = end - start;
//        //封裝數據
//        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
//
//        log.info("數據: {}",operateLog);
//        //紀錄操作日誌
//        operateLogMapper.OperateLogInsert(operateLog);
//
//        return result;
//    }
//}
