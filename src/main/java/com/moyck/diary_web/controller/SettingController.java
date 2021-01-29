package com.moyck.diary_web.controller;

import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.moyck.diary_web.domains.Diary;
import com.moyck.diary_web.domains.ErrorMessage;
import com.moyck.diary_web.domains.NoteBook;
import com.moyck.diary_web.domains.User;
import com.moyck.diary_web.utils.QiniuUtil;
import com.qiniu.util.Auth;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

public class SettingController extends Controller.Util {

    private EbeanDao<Long, User> userDao;

    @Inject
    public SettingController(EbeanDao<Long, User> userDao) {
        this.userDao = userDao;
    }

    @GetAction("/setting/getUploadToken")
    public Result getUploadToken() {
        return  renderText(QiniuUtil.getInstance().getUploadToken());
    }

    @Before()
    public void checkAuthentification(H.Request request) {
        if(request.cookie("uid") == null || request.cookie("token") == null)
            throw renderJson(new ErrorMessage(6, "缺少Token"));
        if (!userDao.findById(Long.valueOf(request.cookie("uid").value())).token.equals(request.cookie("token").value())) {
            throw renderJson(new ErrorMessage(5, "Token 过期"));
        }
    }


}
