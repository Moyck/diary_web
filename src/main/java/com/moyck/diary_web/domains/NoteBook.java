package com.moyck.diary_web.domains;

import act.Act;
import act.util.SimpleBean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notebook")
public class NoteBook implements SimpleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public long uid;
    @Column
    public String name;
    @Column
    public String avatar;

}
