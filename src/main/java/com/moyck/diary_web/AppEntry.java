package com.moyck.diary_web;

import act.Act;
import act.inject.DefaultValue;
import act.util.Output;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;

public class AppEntry {

    @PostAction("register/{name}")
    public String test(String name){
        return name;
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
