package com.moyck.diary_web.controller;

import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.moyck.diary_web.domains.Diary;
import com.moyck.diary_web.domains.User;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;
import java.util.List;

/**
 * id uid createTime content images mood
 */
public class DiaryController extends Controller.Util{

    private EbeanDao<Long, Diary> dao;

    @Inject
    public DiaryController(EbeanDao<Long, Diary> dao) {
        this.dao = dao;
    }

    @PostAction("/diary/create")
    public String create(Diary diary) {
        dao.save(diary);
        return "suc";
    }

    @PostAction("/diary")
    public Iterable<Diary> getDiary(int id) {
        return dao.findBy("uid",id);
    }


}
