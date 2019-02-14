package com.example.backdemo.aop;

import com.example.backdemo.annotation.Mylog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class  OperateAspect {
    @Pointcut("@annotation(com.example.backdemo.annotation.Mylog)")
    private void cut(){
        System.out.println("3");
    }
    // 开始环绕   
    @Around("cut()")
    public void around(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("1");
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        Mylog annotation = method.getAnnotation(Mylog.class);
        System.out.println(annotation.value());
        try{joinPoint.proceed();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("4");
    }
    @Before("cut()")
    public void before(){
        System.out.println("2");
    }
    @After("cut()")
    public void after(){
        System.out.println("5");
    }

}
