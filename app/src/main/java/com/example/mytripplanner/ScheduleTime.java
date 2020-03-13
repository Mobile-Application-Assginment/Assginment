package com.example.mytripplanner;

public class ScheduleTime {

    private long timeId;
    private String timeValue;


    public ScheduleTime (int id, String time) {
        this.timeId = id;
        this.timeValue = time;
    }

    public long getTimeId() { return timeId; }
    public void setTimeId(long id) {
        this.timeId = id;
    }

    public String getTimeValue() { return timeValue; }
    public void setTimeValue(String time) { this.timeValue = time; }
}
