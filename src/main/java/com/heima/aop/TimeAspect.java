package com.heima.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeAspect {


//    @Pointcut ("execution(* com.heima.projectp.service.*.*(..))")
//    @Pointcut ( "execution( * com.heima.projectp.service.EmpService.EmpSelectAll()) ||" +
//            "execution( * com.heima.projectp.service.LogService.insert(java.lang.String))")
    @Pointcut("@annotation(com.heima.aop.CutCut)")
    private void pt(){}



    @Around("pt()") //切入點表達式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.紀錄開始時間
        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        log.info(joinPoint.getSignature() + "方法執行耗時: {} ms", end - begin);

        return result;
    }
}
