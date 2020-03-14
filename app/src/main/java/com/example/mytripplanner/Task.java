/*
 *   NAME    : Task.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The Task class contain the detail information of trip in the database structure.
 *             It will store the information and return to that it asked.
 */

package com.example.mytripplanner;

public class Task {

    // The properties
    private long taskId;
    private long depAirportId;
    private long destAirportId;
    private long userId;
    private long timeId;
    private int adultNum;
    private int childNum;

    // Get method for each properties. It will return the value of property
    public long getTaskId() {
        return taskId;
    }
    public long getDepatureAirportId() { return depAirportId; }
    public long getDestinationAirportId() { return destAirportId; }
    public long getUserId() {
        return userId;
    }
    public long getTimeId() {
        return timeId;
    }
    public int getAdultNum() {
        return adultNum;
    }
    public int getChildNum() {
        return childNum;
    }

    // Set method for each properties. It will update the properties information
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
    public void setDepartureAirportId(long taskId) { this.depAirportId = taskId; }
    public void setDestinationAirportId(long taskId) { this.destAirportId = taskId; }
    public void setUserId(long taskId) {
        this.userId = taskId;
    }
    public void setTimeId(long taskId) {
        this.timeId = taskId;
    }
    public void setAdultNum(int taskId) {
        this.adultNum = taskId;
    }
    public void setChildNum(int taskId) {
        this.childNum = taskId;
    }
}