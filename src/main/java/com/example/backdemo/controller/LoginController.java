package com.example.backdemo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.backdemo.pojo.SysUser;
import com.example.backdemo.service.SysUserService;
import com.example.backdemo.utils.IMoocJSONResult;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.transaction.SystemException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loginController")
public class LoginController{
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("login")
    public IMoocJSONResult login(SysUser sysUser) throws SystemException {
        IMoocJSONResult iMoocJSONResult = new IMoocJSONResult();
        try {
            SysUser user = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("user_name", sysUser.getUserName()));
            if(null == user){
                throw new SystemException("账号不存在!");
            }else{
                simpleAccountRealm.addAccount(user.getUserName(),user.getPassword());
            }
            DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
            defaultSecurityManager.setRealm(simpleAccountRealm);
            // 2、主体提交认证请求
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(),sysUser.getPassword());
            subject.login(token);
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            map.put("user",user);
            iMoocJSONResult.setData(map);
            iMoocJSONResult.setOk(true);
            return iMoocJSONResult;
        } catch (DisabledAccountException e) {
            iMoocJSONResult.setMsg("账号为禁用状态");
            iMoocJSONResult.setOk(false);
            return iMoocJSONResult;
        } catch (IncorrectCredentialsException e) {
            iMoocJSONResult.setMsg("密码错误");
            iMoocJSONResult.setOk(false);
            return iMoocJSONResult;
        } catch (UnknownAccountException e) {
            iMoocJSONResult.setMsg("账号不存在");
            iMoocJSONResult.setOk(false);
            return iMoocJSONResult;
        }
    }

    @PostMapping("logout")
    public IMoocJSONResult logout(SysUser sysUser) throws SystemException {
        try {
            simpleAccountRealm.addAccount(sysUser.getUserName(),sysUser.getPassword());
            DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
            defaultSecurityManager.setRealm(simpleAccountRealm);
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            IMoocJSONResult iMoocJSONResult = new IMoocJSONResult();
            iMoocJSONResult.setOk(true);
            return iMoocJSONResult;
        }catch (Exception e){
            throw new SystemException("登出失败!");
        }
    }
}
