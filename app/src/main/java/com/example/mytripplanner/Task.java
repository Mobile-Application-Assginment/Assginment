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
    private int taskId;
    private int depAirportId;
    private int destAirportId;
    private int userId;
    private int timeId;
    private int adultNum;
    private int childNum;
    private int tripId;

    // constructor to initialize properties
    public Task() {}
    public Task(int task, int departure, int destination, int user, int time, int adult, int child, int trip)
    {
        this.taskId = task;
        this.depAirportId = departure;
        this.destAirportId = destination;
        this.userId = user;
        this.timeId = time;
        this.adultNum = adult;
        this.childNum = child;
        this.tripId = trip;
    }


    // Get method for each properties. It will return the value of property
    public int getTaskId() {
        return taskId;
    }
    public int getDepatureAirportId() { return depAirportId; }
    public int getDestinationAirportId() { return destAirportId; }
    public int getUserId() {
        return userId;
    }
    public int getTimeId() {
        return timeId;
    }
    public int getAdultNum() {
        return adultNum;
    }
    public int getChildNum() {
        return childNum;
    }
    public int getTripId() {
        return tripId;
    }



    // Set method for each properties. It will update the properties information
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public void setDepartureAirportId(int taskId) {
        this.depAirportId = taskId;
    }
    public void setDestinationAirportId(int taskId) {
        this.destAirportId = taskId;
    }
    public void setUserId(int taskId) {
        this.userId = taskId;
    }
    public void setTimeId(int taskId) {
        this.timeId = taskId;
    }
    public void setAdultNum(int taskId) {
        this.adultNum = taskId;
    }
    public void setChildNum(int taskId) {
        this.childNum = taskId;
    }
    public void setTripId(int taskId) {
        this.tripId = taskId;
    }
}