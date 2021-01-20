package com.moyck.diary_web.domains;

import act.Act;
import act.util.SimpleBean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "diary")
public class Diary implements SimpleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public Integer uid;
    @Column
    public String content;
    @Column
    public String images;
    @Column
    public int mood;
    @Column
    public Date createTime;

}
