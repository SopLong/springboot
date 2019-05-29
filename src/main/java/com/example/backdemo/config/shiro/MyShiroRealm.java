package com.example.backdemo.config.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.backdemo.pojo.system.SysUser;
import com.example.backdemo.service.system.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 主要是用来进行身份认证的，即验证用户输入的账号和密码是否正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        // 通过username从数据库中查找
        SysUser user = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("user_name",username));
        if (user == null) {
            return null;
        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户名
//                user.getPassword(), // 密码
//                ByteSource.Util.bytes("Mark"), getName() // realm name
//        );
        System.out.println(getName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return authenticationInfo;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
