package com.moyck.diary_web.controller;

import act.app.ActionContext;
import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.moyck.diary_web.domains.Diary;
import com.moyck.diary_web.domains.ErrorMessage;
import com.moyck.diary_web.domains.User;
import io.ebean.Expr;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * id uid createTime content images mood
 */
public class DiaryController extends Controller.Util {

    private EbeanDao<Long, Diary> diaryDao;
    private EbeanDao<Long, User> userDao;

    @Inject
    public DiaryController(EbeanDao<Long, Diary> diaryDao,EbeanDao<Long, User> userDao) {
        this.diaryDao = diaryDao;
        this.userDao = userDao;
    }

    @PostAction("/diary/create")
    public Diary create(Diary diary) {
        diaryDao.save(diary);
        return diary;
    }

    @PostAction("/diary")
    public Iterable<Diary> getDiary(long id,long nid, Date lastUpdateTime) {
        if (lastUpdateTime == null || lastUpdateTime.getTime() == 0) {
            return diaryDao.findBy("uid", id);
        }
        return diaryDao.q().where(Expr.ge("createTime", lastUpdateTime)).findList();
    }

    @DeleteAction("/diary")
    public void deleteDiary(long id) {
        diaryDao.deleteById(id);
    }

    @PostAction("/diary/update")
    public void updateDiary(long id,long nid, String content, String images) {
        Diary diary = diaryDao.findById(id);
        diary.content = content;
        diary.images = images;
        diary.nid = nid;
        diaryDao.save(diary);
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
