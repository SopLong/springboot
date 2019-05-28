package com.example.backdemo.pojo.system;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.backdemo.content.SysContent;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

public class ResultData {
    private String msg;
    private Integer code=SysContent.SUCCESS_CODE;
    private Object data;
    private CommonPager pager;
    private Integer userCount;
    private boolean success = true;
    public ResultData(){

    }
    public ResultData(Integer code){
        this.code = code;
        setAutoMsg(code,"");

    }
    public ResultData(String msg,Integer code){
        this.code = code;
        setAutoMsg(code,msg);
    }
    public ResultData(Integer code,Object data ){
        setAutoMsg(code,"");
        this.code = code;
        setResDatBody(data);
    }
    public ResultData(Object resData){
        setResDatBody(resData);
    }
    public ResultData(String msg,Integer code,Object data ){
        setAutoMsg(code,msg);
        this.code = code;
        setResDatBody(data);
    }
    // 需要导出excel功能时必须要传入pager
    public ResultData(Object resData, CommonPager pager) {
        setResDatBody(resData);
        this.pager = pager;
    }

    private void setAutoMsg(Integer code,String msg){
        if(StringUtils.isEmpty(msg)){
            switch (code) {
                case SysContent.SUCCESS_CODE:
                    this.msg = SysContent.SUCCESS_MSG;
                    break;
            }
        } else {
            this.msg = msg;
        }
    }
    private void setResDatBody(Object resData){
        if(resData instanceof Page){
            Page page = (Page)resData;
            data = new HashMap<String,Object>();
            ((HashMap) data).put("total",page.getTotal());
            ((HashMap) data).put("items",page.getRecords());
            ((HashMap) data).put("pages",page.getPages());  //总页数
            ((HashMap) data).put("current",page.getCurrent());    //当前页
            ((HashMap) data).put("size",page.getSize());   //每页显示条数
        }else{
            data = resData;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        setResDatBody(data);
    }

    public CommonPager getPager() {
        return pager;
    }

    public void setPager(CommonPager pager) {
        this.pager = pager;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}
