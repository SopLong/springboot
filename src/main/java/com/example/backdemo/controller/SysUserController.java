package com.example.backdemo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.backdemo.pojo.SysUser;
import com.example.backdemo.service.SysUserService;
import com.example.backdemo.utils.IMoocJSONResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

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
    @Transactional(propagation = Propagation.REQUIRED)
    public IMoocJSONResult add(){
        SysUser sysUser = new SysUser();
        sysUser.setName("wangwu");
        sysUser.setAge(18);
        sysUser.setBirthday(new Date());
        sysUserService.insert(sysUser);
        int i = 1/0;
        return new IMoocJSONResult();
    }

    @PostMapping("update")
    public IMoocJSONResult update(){
        SysUser sysUser = new SysUser();
        sysUser.setAge(20);
        sysUser.setBirthday(new Date());
        sysUserService.update(sysUser,new EntityWrapper<SysUser>().eq("name","张三"));
        return new IMoocJSONResult();
    }

    @GetMapping("findAll")
    @Transactional(propagation = Propagation.SUPPORTS)
    public IMoocJSONResult findAll(){
        //分页的实现
        PageHelper.startPage(1,20);

        List<SysUser> sysUsers = sysUserService.selectList(new EntityWrapper<SysUser>());
        return new IMoocJSONResult();
    }
}

