package com.moyck.diary_web.controller;

import act.controller.Controller;
import act.db.ebean.EbeanDao;
import com.moyck.diary_web.domains.ErrorMessage;
import com.moyck.diary_web.domains.NoteBook;
import com.moyck.diary_web.domains.User;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;

/**
 * id uid createTime content images mood
 */
public class NotebookController extends Controller.Util {

    private EbeanDao<Long, NoteBook> notebookDao;
    private EbeanDao<Long, User> userDao;

    @Inject
    public NotebookController(EbeanDao<Long, NoteBook> notebookDao, EbeanDao<Long, User> userDao) {
        this.notebookDao = notebookDao;
        this.userDao = userDao;
    }

    @PostAction("/notebook/create")
    public void create(NoteBook noteBook) {
        notebookDao.save(noteBook);
    }

    @GetAction("/notebooks")
    public Iterable<NoteBook> getAllNoteBooks(long uid) {
       return notebookDao.findBy("uid",uid);
    }

    @PostAction("/notebook/update")
    public void update(NoteBook noteBook) {
       NoteBook book =  notebookDao.findById(noteBook.id);
       book.avatar = noteBook.avatar;
       book.name = noteBook.name;
       notebookDao.save(book);
    }


    @Before()
    public void checkAuthentification(H.Request request) {
        try {
            if (!userDao.findById(Long.parseLong(request.paramVal("uid"))).token.equals(request.cookie("token").value())){
                throw renderJson(new ErrorMessage(5,"Token 过期,请重新登陆"));
            }
        }catch (Exception e){
            throw renderJson(new ErrorMessage(5,"Token 过期,请重新登陆"));
        }
    }


}
