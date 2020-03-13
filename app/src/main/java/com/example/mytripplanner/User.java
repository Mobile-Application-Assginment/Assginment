package com.example.mytripplanner;

public class User {

    private long userId;
    private String userName;


    public User (int id, String name) {
        this.userId = id;
        this.userName = name;
    }

    public long getUserId() { return userId; }
    public void setUserId(long id) {
        this.userId = id;
    }

    public String getUserName() { return userName; }
    public void setUserName(String name) { this.userName = name; }
}
