package com.moyck.diary_web.controller;

import act.Act;
import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.moyck.diary_web.domains.LoginRequest;
import com.moyck.diary_web.domains.User;
import io.ebean.DuplicateKeyException;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;

/**
 * id userName password createTime
 *
 *
 *
 *
 */

public class UserController extends Controller.Util {

    private EbeanDao<Long, User> dao;

    @Inject
    public UserController(EbeanDao<Long, User> dao) {
        this.dao = dao;
    }

    @PostAction("/user/login")
    public User login(String password,String userName) {
        User findUser = dao.findOneBy("userName",userName);
        return findUser;
    }

    @PostAction("/user/changePassword")
    public User changePassword(String userName,String newPassword) {
        User findUser = dao.findOneBy("userName",userName);
        findUser.password = Act.crypto().passwordHash( newPassword);
        dao.save(findUser);
        return findUser;
    }

    @PostAction("/user/register")
    public String register(User user) {
        try {
            if (user.userName.length() < 6 || user.userName.length() > 15){
                return "账号需要在8 - 15个字符之间";
            }
            if (user.password.length() < 8 || user.password.length() > 20){
                return "密码需要在8 - 20个字符之间";
            }
            user.password = Act.crypto().passwordHash( user.password);
            dao.save(user);
            return  user.password;
        }catch (DuplicateKeyException e){
            return "账号已存在";
        }
    }

}
