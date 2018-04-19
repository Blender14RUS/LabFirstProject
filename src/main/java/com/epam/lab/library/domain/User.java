package com.epam.lab.library.domain;

public class User {

    private Long id;
    private String login;
    private String pass;
    private String name;

    private AccessLevel accessLevel;

    public User() {}

    public User(Long id, String login, String pass, String name, AccessLevel accessLevel) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.accessLevel = accessLevel;
    }

    public User(Long id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
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

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
