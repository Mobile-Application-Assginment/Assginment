package com.example.mytripplanner;

public class MyItem {

    private String name;
    private String departure;
    private String destination;
    private String time;
    private String adult;
    private String child;
    private String trip;

    public MyItem () {}
    public MyItem (String name, String departure, String destination, String time, String adult, String child, String trip) {
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.adult = adult;
        this.child = child;
        this.trip = trip;
    }


    public String getName(){
        return name;
    }
    public String getDeparture(){
        return departure;
    }
    public String getDestination(){
        return destination;
    }
    public String getTime(){
        return time;
    }
    public String getAdult(){
        return adult;
    }
    public String getChild(){
        return child;
    }
    public String getTrip(){
        return trip;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setDeparture(String departure){
        this.departure = departure;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setAdult(String adult){
        this.adult = adult;
    }
    public void setChild(String child){
        this.child = child;
    }
    public void setTrip(String trip){
        this.trip = trip;
    }
}
