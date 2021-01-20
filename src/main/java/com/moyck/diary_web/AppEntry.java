package com.moyck.diary_web;

import act.Act;
import act.inject.DefaultValue;
import act.util.Output;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;

public class AppEntry {


    public static void main(String[] args) throws Exception {
        Act.start();
    }

}

/*
User
注册
登陆
改密码
绑定邮箱

Diary
发布日记
删除
更新
获取日记列表

Notebook
新建日记
日记移动


 */