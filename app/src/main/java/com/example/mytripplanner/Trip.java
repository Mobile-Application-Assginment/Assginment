package com.example.mytripplanner;

public class Trip {

    // The properties
    private int tripId;
    private String tripType;


    // The constructor which has two argument for each properties
    public Trip (int id, String type) {
        this.tripId = id;
        this.tripType = type;
    }

    // Get method for each properties. It will return the value of property
    public int getTripId() { return tripId; }
    public String getTripType() { return tripType; }


    // Set method for each properties. It will update the properties information
    public void setTripId(int id) {
        this.tripId = id;
    }
    public void setTripType(String type) { this.tripType = type; }
}
