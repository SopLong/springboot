package com.example.backdemo.controller;

import com.example.backdemo.pojo.Resource;
import com.example.backdemo.utils.IMoocJSONResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //@RestController = @Controller + @ResponseBody
public class RedisController {

    private StringRedisTemplate stringRedisTemplate;

    public void test(){
    }
}
