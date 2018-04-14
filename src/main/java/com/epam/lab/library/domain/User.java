package com.epam.lab.library.domain;

import java.util.Date;

public class User {

    private Long id;
    private String login;
    private Date dateOfBirth;
    private String name;

    public User() {}

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public long getId() {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
