package com.example.backdemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class User {
    private String name;
    @JsonIgnore   //忽略不会显示给前端
    private String password;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8") //時間格式
    private Date birthday;
    @JsonInclude(JsonInclude.Include.NON_NULL)   //如果desc是空或者是null的话忽略不会显示给前端
    private String desc;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getDesc() {
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", desc='" + desc + '\'' +
                '}';
    }
}
