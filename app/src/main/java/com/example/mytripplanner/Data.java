/*
 *   NAME    : Data.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The Data class is like structure for share the information
 *              throw the indent.
 */
package com.example.mytripplanner;

import java.io.Serializable;

public class Data implements Serializable {
    // properties of Data object
    public  String name;
    public  String departure;
    public  String destination;
    public  String adultNumber;
    public  String childNumber;
    public  String tripType;

//    public  Data() {};

    // constructor for setting default values
    public  Data(String name, String departure, String destination, String adultNumber, String childNumber,String tripType){
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.adultNumber = adultNumber;
        this.childNumber= childNumber;
        this.tripType = tripType;
    }
}
