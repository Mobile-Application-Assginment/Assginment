/*
 *   NAME    : Data.java
 *   Project: Mobile Application Development - Assignment 1
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Feb. 7, 2020
 *   PURPOSE : The Data class is like structure for share the information throw the indent.
 */
package com.example.mytripplanner;

import java.io.Serializable;

public class Data implements Serializable {
    public  String name;
    public  String departure;
    public  String destination;
    public  String adultNumber;
    public  String childNumber;

//    public  Data() {};

    public  Data(String name, String departure, String destination, String adultNumber, String childNumber){
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.adultNumber = adultNumber;
        this.childNumber= childNumber;
    }
}
