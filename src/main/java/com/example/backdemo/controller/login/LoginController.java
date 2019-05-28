package com.example.backdemo.controller.login;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.backdemo.pojo.system.ResultData;
import com.example.backdemo.pojo.system.SysUser;
import com.example.backdemo.service.system.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultData login(SysUser sysUser) throws SystemException {
        ResultData resultData = new ResultData();
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
            resultData.setData(map);
            return resultData;
        } catch (DisabledAccountException e) {
            resultData.setMsg("账号为禁用状态");
            resultData.setSuccess(false);
            return resultData;
        } catch (IncorrectCredentialsException e) {
            resultData.setMsg("密码错误");
            resultData.setSuccess(false);
            return resultData;
        } catch (UnknownAccountException e) {
            resultData.setMsg("账号不存在");
            resultData.setSuccess(false);
            return resultData;
        }
    }

    @PostMapping("logout")
    public ResultData logout(SysUser sysUser) throws SystemException {
        try {
            simpleAccountRealm.addAccount(sysUser.getUserName(),sysUser.getPassword());
            DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
            defaultSecurityManager.setRealm(simpleAccountRealm);
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            ResultData resultData = new ResultData();
            resultData.setSuccess(true);
            return resultData;
        }catch (Exception e){
            throw new SystemException("登出失败!");
        }
    }
}
