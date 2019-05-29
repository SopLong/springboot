package com.example.backdemo.controller.system;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.backdemo.pojo.system.ResultData;
import com.example.backdemo.pojo.system.SysUser;
import com.example.backdemo.service.system.SysUserService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("getList")
    public ResultData getList(Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject);
        //分页的实现
        PageHelper.startPage(1,20);
        return new ResultData(sysUserService.selectList(new EntityWrapper<SysUser>()));
    }
}

