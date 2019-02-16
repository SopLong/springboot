package com.example.backdemo.controller;

import com.example.backdemo.annotation.Mylog;
import com.example.backdemo.utils.IMoocJSONResult;
import com.example.backdemo.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //@RestController = @Controller + @ResponseBody
public class HelloController {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
//    @Mylog("ssss")
    public IMoocJSONResult hello(){
       Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        return IMoocJSONResult.ok(bean);
    }
}
