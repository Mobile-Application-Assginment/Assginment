/*
 *   NAME    : FlightInfoResult.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The FlightInfoResult class has been created to use Data structure
 *             ArrayList and HashMap to receive data from json server as json format
 *             and change into string format
 */

package com.example.mytripplanner;

import java.util.ArrayList;
import java.util.HashMap;

public class FlightInfoResult {
    // Data structure to store data received from json server
    private ArrayList<HashMap<String,String>> data;
    // getter for data
    public  ArrayList<HashMap<String,String>> getData(){return data;}
    // setter for data
    public  void setData(ArrayList<HashMap<String,String>>data){
        this.data = data;
    }
}
