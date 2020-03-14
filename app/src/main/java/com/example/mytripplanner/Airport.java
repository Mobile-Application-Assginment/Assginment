/*
 *   NAME    : Airport.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The Airport class contain the information of airport in the database structure.
 *             It will store the information and return to that it asked.
 *             Through this class Airport information will be stored and retrieved from to Database
 */

package com.example.mytripplanner;

public class Airport {

    // The properties
    private int airportId;
    private String airportName;


    // The constructor which has two argument for each properties
    public Airport (int id, String name) {
        this.airportId = id;
        this.airportName = name;
    }

    // Get method for each properties. It will return the value of property
    public int getAirportId() { return airportId; }
    public String getAirportName() { return airportName; }


    // Set method for each properties. It will update the properties information
    public void setAirportName(String name) {
        this.airportName = name;
    }
    public void setAirportId(int id) {
        this.airportId = id;
    }
}
