package com.mafr.latihan.form.model;

import java.sql.Date;

public class Members {
    private int memberid;
    private String name;
    private Date birthday;
    private String parentsname;
    private int phone;
    private String email;
    private boolean hadlesson;

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getParentsname() {
        return parentsname;
    }

    public void setParentsname(String parentsname) {
        this.parentsname = parentsname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHadlesson() {
        return hadlesson;
    }

    public void setHadlesson(boolean hadlesson) {
        this.hadlesson = hadlesson;
    }
}
