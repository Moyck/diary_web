package com.moyck.diary_web.domains;

import act.Act;
import act.util.SimpleBean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements SimpleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public String password;
    @Column
    public String userName;
    @Column
    public Date createTime;

    public static String getPasswordHash(String password) {
        return Act.crypto().passwordHash(password);
    }


}
