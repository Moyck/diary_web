package com.moyck.diary_web.domains;

import act.util.SimpleBean;

import javax.persistence.Column;
import javax.persistence.Entity;

public class ErrorMessage  {

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    String msg;
    int code;

    public ErrorMessage(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
