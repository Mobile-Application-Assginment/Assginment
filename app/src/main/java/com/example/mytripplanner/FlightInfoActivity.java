/*
 *   NAME    : FlightInfoActivity.java
 *   Project: Mobile Application Development - Assignment 1
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Feb. 7, 2020
 *   PURPOSE : The FlightInfoActivity class shows the city which is departure and destination for flight.
 *             It gives a list of the city for the user. Nothing more than that.
 */
package com.example.mytripplanner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FlightInfoActivity extends Activity {

    ListDB db = new ListDB(this);

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flightinfo);


        // Make a list view adapter with a array of cities
        ArrayAdapter listboxAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, db.getAirportList());


        // Find a list view ID and set an adapter in list
        ListView listView = findViewById(R.id.list_schedule);
        listView.setAdapter(listboxAdapter);

        // The button which is back to the Home screen
        Button btn = findViewById(R.id.btn_flight);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent intent = new Intent(FlightInfoActivity.this,PerInfoActivity.class );
                                       startActivity(intent);
                                   }
                               }
        );

        // The button which is back to the Home screen
        Button btnCall = findViewById(R.id.btn_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Uri webpage = Uri.parse("https://www.conestogac.on.ca/");
                                       Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                                       if (intent.resolveActivity(getPackageManager()) != null) {
                                           startActivity(intent);
                                       }
                                   }
                               }
        );

    }
}
