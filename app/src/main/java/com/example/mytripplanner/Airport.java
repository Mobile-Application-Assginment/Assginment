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
