package com.example.backdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cookie
 * @Date: 2018/7/26 15:09
 * @Description: 全局捕获异常和自定义全局捕获异常
 */
@Slf4j
@ControllerAdvice  //不指定包默认加了@Controller和@RestController都能控制
//@ControllerAdvice(basePackages ="com.example.backdemo.controller")
public class GlobalExceptionHandler {
    /**
     * 全局异常处理，反正异常返回统一格式的map
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> exceptionHandler(Exception ex){
        Map<String,Object> map  = new HashMap<String,Object>();

        if(ex instanceof NullPointerException ){
            log.error("空指针异常：",ex.getMessage());
            map.put("msg:空指针异常:",ex.getMessage());

        }else if(ex instanceof ArrayIndexOutOfBoundsException){
            log.error("数组下标越界异常：",ex.getMessage());
            map.put("msg:数组下标越界异常:",ex.getMessage());

        }else if(ex instanceof DuplicateKeyException){
            log.error("不允许重复添加数据：",ex.getMessage());
            map.put("msg:不允许重复添加数据:",ex.getMessage());

        }else if(ex instanceof SystemException){
            log.error("异常信息：",ex);//自定义异常信息
            map.put("msg:",ex.getMessage());

        }else{
            log.error("发生未知错误：",ex.getMessage());
            map.put("msg:发生未知错误!",ex.getLocalizedMessage());

        }

        map.put("code",1001);
        map.put("mag",ex.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }
}
