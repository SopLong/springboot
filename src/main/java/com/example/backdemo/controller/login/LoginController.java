package com.example.backdemo.controller.login;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.backdemo.controller.system.BaseController;
import com.example.backdemo.pojo.system.ResultData;
import com.example.backdemo.pojo.system.SysUser;
import com.example.backdemo.service.system.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.SystemException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loginController")
public class LoginController extends BaseController {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @PostMapping("login")
    public ResultData login(SysUser sysUser) throws SystemException {
        ResultData resultData = new ResultData();
        try {
            Subject subject = (Subject) SecurityUtils.getSubject().getPrincipal();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword());
            subject.login(token);
            String authorization = (String) subject.getSession().getId();
            resultData.setSuccess(true);
            resultData.setData(authorization); //将authorization传给前端，用于MySessionManager中请求的验证
            resultData.setMsg("登陆成功");
        } catch (IncorrectCredentialsException e) {
            resultData.setMsg("密码错误");
        } catch (LockedAccountException e) {
            resultData.setMsg("该用户已被禁用");
        } catch (AuthenticationException e) {
            resultData.setMsg("该用户不存在");
        }
        return resultData;
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
