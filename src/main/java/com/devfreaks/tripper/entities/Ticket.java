package com.devfreaks.tripper.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tck_ticket")
public class Ticket {

    @Id
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "passport_no", nullable = false, unique = true)
    private String passportNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }
}
