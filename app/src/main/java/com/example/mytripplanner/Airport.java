package com.example.mytripplanner;

public class Airport {

    private long airportId;
    private String airportName;


    public Airport (int id, String name) {
        this.airportId = id;
        this.airportName = name;
    }


    public long getAirportId() { return airportId; }
    public void setAirportId(long id) {
        this.airportId = id;
    }

    public String getAirportName() { return airportName; }
    public void setAirportName(String name) { this.airportName = name; }
}
