package com.example.backdemo.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.example.backdemo.content.ErrorEnum;
import com.example.backdemo.pojo.system.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        JSONObject jsonObject = new JSONObject();
        if(null == sysUser){
            jsonObject.put("code", ErrorEnum.E_20011.getErrorCode());
            jsonObject.put("msg", ErrorEnum.E_20011.getErrorMsg());
            PrintWriter out = null;
            HttpServletResponse res = (HttpServletResponse) response;
            try {
                res.setCharacterEncoding("UTF-8");
                res.setContentType("application/json");
                out = response.getWriter();
                out.println(jsonObject);
            } catch (Exception e) {
            } finally {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
