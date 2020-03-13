/*
 *   NAME    : User.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The Task class contain the user information in the database structure.
 *             It will store the information and return to that it asked.
 */

package com.example.mytripplanner;

public class User {

    // The properties
    private long userId;
    private String userName;


    // The constructor which has two argument for each properties
    public User (int id, String name) {
        this.userId = id;
        this.userName = name;
    }


    // Get method for each properties. It will return the value of property
    public long getUserId() { return userId; }
    public String getUserName() { return userName; }

    // Set method for each properties. It will update the properties information
    public void setUserId(long id) {
        this.userId = id;
    }
    public void setUserName(String name) { this.userName = name; }
}
