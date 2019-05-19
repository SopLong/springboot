package com.example.backdemo.service.impl.system;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.backdemo.mapper.system.SysUserMapper;
import com.example.backdemo.pojo.system.SysUser;
import com.example.backdemo.service.system.SysUserService;
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
