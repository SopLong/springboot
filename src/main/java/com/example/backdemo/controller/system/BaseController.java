package com.example.backdemo.controller.system;

import com.alibaba.fastjson.JSON;
import com.example.backdemo.pojo.system.SysUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;

public class BaseController {
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    public SysUser getUserByHeader(ServletRequest request) throws Exception{
        //前端ajax的headers中必须传入Authorization的值
        String id = WebUtils.toHttp(request).getHeader("Authorization");
        Session session = redisSessionDAO.readSession(id);
        Object obj = ((Session) session).getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
        String userStr = JSON.toJSON(coll.getPrimaryPrincipal()).toString();
        SysUser user = JSON.parseObject(userStr, SysUser.class);
        return user;
    }
}
