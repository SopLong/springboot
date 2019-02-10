package com.example.backdemo.controller;

import com.example.backdemo.domain.IMoocJSONResult;
import com.example.backdemo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController     //@RestController = @Controller + @ResponseBody
public class UserController {
    @RequestMapping("/hello")
    public IMoocJSONResult hello(){
        User user = new User();
        user.setAge(11);
        user.setName("張三");
        user.setBirthday(new Date());
        user.setPassword("123456");
        user.setDesc("hello Stringboot!55555");
        return IMoocJSONResult.ok(user);
    }
}
