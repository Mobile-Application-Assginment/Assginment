/*
 *   NAME    : TripInfoActivity.java
 *   Project: Mobile Application Development - Assignment 1
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The TripInfoActivity class has been created to provide a method for user input
 *             such as a destination and adult and child number. The TripinfoActivity also has the
 *             ability to show a list of destination cities in a form of listview.
 *             After receiving all data send it to ConfirmInfoActivity throw intent
 */

package com.example.mytripplanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class TripInfoActivity extends Activity {
    // properties receive from previous activity
    static Integer[] LIST_PERSONNUM = {0, 1, 2, 3, 4};
    String custName = "";
    String departure = "";
    String destination = "";
    String adultNum = "0";
    String childNum = "0";
    String tripType = "OneWay";
    // create Database for store travel information
    ListDB db = new ListDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripinfo);

        // Receive data from Perinfo activity throw intent
        Intent intent_receive = getIntent();
        Data data_receive = (Data) intent_receive.getSerializableExtra("data");

        // Get each value from view
        TextView txtName = findViewById(R.id.txt_name);

        final TextView txtDeparture = findViewById(R.id.txt_departure);
        final TextView txtDestination = findViewById(R.id.txt_destination);

        custName = data_receive.name;
        txtName.setText("CUSTOMER: " + custName);
        departure = data_receive.departure;
        txtDeparture.setText(departure);

        // Adapter for list of trip information
        // Adapters are needed to place data intelligently within the list
        // and in order to handle list selection
        ArrayAdapter listboxAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, db.getAirportList());

        ListView listView = findViewById(R.id.list_schedule);
        // Set the adapter into list for trip
        listView.setAdapter(listboxAdapter);

        // Event handler for click one listview item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                destination = parent.getItemAtPosition(position).toString();
                txtDestination.setText(destination);
            }

        });

        // Using spinner Adapter to show options for number of adult and children
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, LIST_PERSONNUM);
        Spinner adultSpinner = findViewById(R.id.sp_adult);
        Spinner childSpinner = findViewById(R.id.sp_child);

        // Set adapter to spinner
        adultSpinner.setAdapter(spinnerAdapter);
        childSpinner.setAdapter(spinnerAdapter);

        // Event handler for item selected in spinner for adult number
        adultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adultNum = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Event handler for item selected in spinner for child number
        childSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                childNum = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Add Radio button
        final RadioGroup rgTripType = (RadioGroup) findViewById(R.id.rg_tripType);

        rgTripType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb_oneway){
                    tripType = "OneWay";
                }else{
                    tripType = "Round";
                }
            }
        });

        // Event handler for confirm button click - move to confirminfoactivity screen
        Button btn = findViewById(R.id.btn_confirm);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                        int x = db.getAirportId(departure);
                                       int b = db.getUserId(custName);
                                        int a = db.getTripId(tripType);


                                       Task task = new Task();
                                       task.setDepartureAirportId(db.getAirportId(departure));
                                       task.setDestinationAirportId(db.getAirportId(destination));

                                       task.setAdultNum(Integer.parseInt(adultNum));
                                       task.setChildNum(Integer.parseInt(childNum));
                                       task.setUserId(db.getUserId(custName));
                                       task.setTripId((db.getTripId(tripType)));
                                       db.insertTask(task);


                                       Data data_send = new Data(custName, departure, destination, adultNum, childNum, tripType);
                                       Intent intent_send = new Intent(TripInfoActivity.this, ConfirmInfoActivity.class);
                                       intent_send.putExtra("data", data_send);

                                       startActivity(intent_send);
                                   }
                               }
        );

    }

    // Make a menu option
    // In order to display a menu, use inflate
    // This method is a part of the parent Activity class
    // and must be overridden
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tripmenu, menu);
        return true;
    }

    // Response the activity which is selected by user
    // Menu is defined in the menu file under res/menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;
        Intent intent = null;
        switch(item.getItemId()) {
            // When select fiight info menu move into FlightInfoActivity
            case R.id.menu_flightinfo:
                intent = new Intent(this,FlightInfoActivity.class );
                startActivity(intent);
                result = true;
                break;
            // When select home menu move into PerInfoActivity
            case R.id.menu_home:
                intent = new Intent(this, PerInfoActivity.class);
                startActivity(intent);
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }
}
