package com.example.backdemo.pojo;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

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
    /**
     * 角色
     */
    private Integer role;
}
