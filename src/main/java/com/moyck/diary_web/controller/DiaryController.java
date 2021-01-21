package com.moyck.diary_web.controller;

import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.moyck.diary_web.domains.Diary;
import com.moyck.diary_web.domains.User;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;
import java.util.Date;
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
    public Iterable<Diary> getDiary(long id, Date lastUpdateTime) {
        if (lastUpdateTime == null || lastUpdateTime.getTime() == 0){
            return dao.findBy("uid",id);
        }
//        dao.createQuery("Select * from diary");
        return        dao.findBy().
    }

    @DeleteAction("/diary")
    public String deleteDiary(long id) {
        dao.deleteById(id);
        return "suc";
    }

    @PostAction("/diary/update")
    public String updateDiary(long id,String content,String images) {
        Diary diary = dao.findById(id);
        diary.content = content;
        diary.images = images;
        dao.save(diary);
        return "suc";
    }


}
