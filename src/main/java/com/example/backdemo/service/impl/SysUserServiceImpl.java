package com.example.backdemo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.backdemo.mapper.SysUserMapper;
import com.example.backdemo.pojo.SysUser;
import com.example.backdemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yux123
 * @since 2019-02-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
