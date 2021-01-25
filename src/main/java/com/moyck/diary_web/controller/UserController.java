package com.moyck.diary_web.controller;

import act.Act;
import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.moyck.diary_web.domains.ErrorMessage;
import com.moyck.diary_web.domains.LoginRequest;
import com.moyck.diary_web.domains.User;
import io.ebean.DuplicateKeyException;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;
import org.osgl.mvc.result.Result;
import java.util.Date;
import java.util.UUID;

/**
 * id userName password createTime
 */

public class UserController extends Controller.Util {

    private EbeanDao<Long, User> dao;

    @Inject
    public UserController(EbeanDao<Long, User> dao) {
        this.dao = dao;
    }

    @PostAction("/user/login")
    @act.util.PropertySpec("id,userName,createTime,token,email,name,wrongPasswordTime")
    public User login(String password, String userName) {
        if (password == null || userName == null){
            throw renderJson(new ErrorMessage(3,"账号或密码错误"));
        }
        User findUser = dao.findOneBy("userName", userName);
        if (findUser == null){
            throw renderJson(new ErrorMessage(3,"账号或密码错误"));
        }
        if (findUser.verifyPassword(password)){
            findUser.wrongPasswordTime = 0;
            findUser.token = UUID.randomUUID().toString();
            dao.save(findUser);
            return findUser;
        }else {
            findUser.wrongPasswordTime ++;
            dao.save(findUser);
            throw renderJson(new ErrorMessage(findUser.wrongPasswordTime < 3 ? 3 : 4,"账号或密码错误"));
        }
    }

    @PostAction("/user/changePassword")
    @act.util.PropertySpec("id,userName,createTime,Token,email,name")
    public User changePassword(String userName, String newPassword) {
        User findUser = dao.findOneBy("userName", userName);
        findUser.password = Act.crypto().passwordHash(newPassword);
        findUser.token = UUID.randomUUID().toString();
        dao.save(findUser);
        return findUser;
    }

    @PostAction("/user/register")
    @act.util.PropertySpec("id,userName,createTime,token,email,name")
    public User register(User user) {
        try {
            if (user.userName.length() < 6 || user.userName.length() > 15) {
                throw renderJson(new ErrorMessage(0,"账号需要在6 - 15个字符之间"));
            }
            if (user.password.length() < 8 || user.password.length() > 20) {
                throw renderJson(new ErrorMessage(1,"密码需要在8 - 20个字符之间"));
            }
            user.password = User.getPasswordHash(user.password);
            user.token = UUID.randomUUID().toString();
            user.createTime = new Date();
            dao.save(user);
            return user;
        }catch (DuplicateKeyException exception){
            throw renderJson(new ErrorMessage(2,"账号已经被注册"));
        }
    }

}
