package com.mafr.latihan.form.model;

import javax.validation.constraints.NotEmpty;

public class Users {
    private int id;
    @NotEmpty(message = "Tidak boleh kosong!")
    private String username, password;
    private boolean islogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIslogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }
}
