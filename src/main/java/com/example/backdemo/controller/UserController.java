package com.example.backdemo.controller;

import com.example.backdemo.domain.IMoocJSONResult;
import com.example.backdemo.domain.Resource;
import com.example.backdemo.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController     //@RestController = @Controller + @ResponseBody
public class UserController {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public IMoocJSONResult hello(){
       Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        return IMoocJSONResult.ok(bean);
    }
}
