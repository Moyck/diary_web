package com.moyck.diary_web.controller;

import act.controller.Controller;
import act.db.ebean.EbeanDao;
import act.db.jpa.*;
import com.moyck.diary_web.domains.User;
import io.ebean.DuplicateKeyException;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;

public class UserController extends Controller.Util {

    private EbeanDao<Long, User> dao;

    @Inject
    public UserController(EbeanDao<Long, User> dao) {
        this.dao = dao;
    }

    @PostAction("/user/register")
    public String register(User user) {
        try {
            dao.save(user);
        }catch (DuplicateKeyException e){
            return "账号已存在";
        }
        return "suc";
    }

}
