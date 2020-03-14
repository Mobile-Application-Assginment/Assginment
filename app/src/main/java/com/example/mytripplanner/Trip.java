/*
 *   NAME    : Trip.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The Trip class contain the detail information of trip in the database structure.
 *             It will store the information and return to that it asked.
 */

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
