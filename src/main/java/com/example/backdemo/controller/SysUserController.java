package com.example.backdemo.controller;


import com.example.backdemo.pojo.SysUser;
import com.example.backdemo.service.SysUserService;
import com.example.backdemo.utils.IMoocJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yux123
 * @since 2019-02-16
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("add")
    public IMoocJSONResult add(){
        SysUser sysUser = new SysUser();
        sysUser.setName("张三");
        sysUser.setAge(18);
        sysUser.setBirthday(new Date());
        sysUserService.insert(sysUser);
        return new IMoocJSONResult();
    }

}

