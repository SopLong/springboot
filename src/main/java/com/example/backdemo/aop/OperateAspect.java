package com.example.backdemo.aop;

import com.example.backdemo.annotation.Mylog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class  OperateAspect {
    @Pointcut("@annotation(com.example.backdemo.annotation.Mylog)")
    private void cut(){
        log.debug("3");
    }
    // 开始环绕   
    @Around("cut()")
    public void around(ProceedingJoinPoint joinPoint)throws Throwable{
        log.debug("1");
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        Mylog annotation = method.getAnnotation(Mylog.class);
        log.debug(annotation.value());
        try{joinPoint.proceed();
        }catch (Exception e){
            e.printStackTrace();
        }
        log.debug("4");
    }
    @Before("cut()")
    public void before(){
        log.debug("2");
    }
    @After("cut()")
    public void after(){
        log.debug("5");
    }

}
