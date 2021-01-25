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
    public long id;
    @Column
    public String password;
    @Column
    public String userName;
    @Column
    public Date createTime;
    @Column
    public String token;
    @Column
    public String email;
    @Column
    public String name;
    @Column
    public Integer wrongPasswordTime = 0;

    public static String getPasswordHash(String password) {
        return Act.crypto().passwordHash(password);
    }

    public  Boolean verifyPassword(String password) {
        return Act.crypto().verifyPassword(password,this.password);
    }


}
