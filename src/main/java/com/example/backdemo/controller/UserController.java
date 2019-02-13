package com.example.backdemo.controller;

import com.example.backdemo.utils.IMoocJSONResult;
import com.example.backdemo.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //@RestController = @Controller + @ResponseBody
public class UserController {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public IMoocJSONResult hello(){
       Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        int id;
        id = 1/0;
        return IMoocJSONResult.ok(bean);
    }
}
