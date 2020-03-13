package com.example.mytripplanner;

public class Task {

    private long taskId;
    private long airportId;
    private long userId;
    private long timeId;


    public long getTaskId() {
        return taskId;
    }
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getAirportId() { return airportId; }
    public void setAirportId(long taskId) { this.airportId = taskId; }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long taskId) {
        this.userId = taskId;
    }

    public long getTimeId() {
        return timeId;
    }
    public void setTimeId(long taskId) {
        this.timeId = taskId;
    }
}