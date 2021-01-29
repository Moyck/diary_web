package com.moyck.diary_web.domains;

import act.Act;
import act.util.SimpleBean;
import io.ebean.config.JsonConfig;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "diary")
public class Diary implements SimpleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public long uid;
    @Column
    public String content;
    @Column
    public String title;
    @Column
    public String images;
    @Column
    public int mood;
    @Column
    public long createTime;
    @Column
    public long nid;

}
