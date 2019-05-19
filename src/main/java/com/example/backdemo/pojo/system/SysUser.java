package com.example.backdemo.pojo.system;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName("users")
@Data
public class SysUser implements Serializable {
    private Integer id;
    /**
     * 学号
     */
    private String userName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 密码
     */
    private String password;
    @TableField(exist = false)
    private List<String> roles;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
}
