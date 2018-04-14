package com.epam.lab.library.domain;

import java.util.Date;

public class User {

    private Long id;
    private String login;
    private String pass;
    private String name;
    private String access_level;

    public User() {}

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(Long id, String login, String name) {
        this.id = id;
        this.login = login;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }
}
