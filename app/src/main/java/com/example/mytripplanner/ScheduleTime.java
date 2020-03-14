/*
 *   NAME    : ScheduleTime.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The ScheduleTime class contain the information of time of flight schedule in the database structure.
 *             It will store the information and return to that it asked.
 */

package com.example.mytripplanner;

public class ScheduleTime {

    // The properties
    private int timeId;
    private String timeValue;


    // The constructor which has two argument for each properties
    public ScheduleTime (int id, String time) {
        this.timeId = id;
        this.timeValue = time;
    }

    // Get method for each properties. It will return the value of property
    public int getTimeId() { return timeId; }
    public String getTimeValue() { return timeValue; }


    // Set method for each properties. It will update the properties information
    public void setTimeId(int id) {
        this.timeId = id;
    }
    public void setTimeValue(String time) { this.timeValue = time; }
}
