/*
 *   NAME    : Airport.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The Airport class contain the information of airport in the database structure.
 *             It will store the information and return to that it asked.
 */

package com.example.mytripplanner;

public class Airport {

    // The properties
    private long airportId;
    private String airportName;


    // The constructor which has two argument for each properties
    public Airport (int id, String name) {
        this.airportId = id;
        this.airportName = name;
    }

    // Get method for each properties. It will return the value of property
    public long getAirportId() { return airportId; }
    public String getAirportName() { return airportName; }


    // Set method for each properties. It will update the properties information
    public void setAirportName(String name) { this.airportName = name; }
    public void setAirportId(long id) {
        this.airportId = id;
    }
}
