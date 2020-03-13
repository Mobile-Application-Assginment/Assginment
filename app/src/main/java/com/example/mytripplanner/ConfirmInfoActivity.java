/*
 *   NAME    : ConfirmInfoActivity.java
 *   Project: Mobile Application Development - Assignment 1
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Feb. 7, 2020
 *   PURPOSE : The ConfirmInfoActivity class has been created to provide a result of selection
 *             such as a user name and city of departure. The ConfirmInfoActivity shows as a table
 *             which is the all information at once.
 */
package com.example.mytripplanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class ConfirmInfoActivity extends Activity {
    String custName = "";
    String departure = "";
    String destination = "";
    String adultNum = "0";
    String childNum = "0";
    Activity act = this;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirminfo);

        // Receive data from Tripinfo activity throw intent
        Intent intent_receive = getIntent();
        Data data_receive = (Data) intent_receive.getSerializableExtra("data");

        // Get the each elements ID
        TextView customerName = findViewById(R.id.user_name);
        TextView numberAdult = findViewById(R.id.adult_num);
        TextView numberChild = findViewById(R.id.child_num);
        TextView departureCity = findViewById(R.id.depature_result);
        TextView detinationCity = findViewById(R.id.detination_result);

        // Get each value from intent
        custName = data_receive.name;
        departure = data_receive.departure;
        destination = data_receive.destination;
        adultNum = data_receive.adultNumber;
        childNum = data_receive.childNumber;

        // Print the value into the XML element
        customerName.setText(custName);
        departureCity.setText(departure);
        detinationCity.setText(destination);
        numberAdult.setText(adultNum);
        numberChild.setText(childNum);

        Button btn = findViewById(R.id.btn_call);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {


                                       Uri webpage = Uri.parse("https://flightaware.com/live/");
                                       Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                                       if (intent.resolveActivity(getPackageManager()) != null) {
                                           startActivity(intent);
                                       }
                                   }
                               }
        );

    }

    // Make a menu option
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tripmenu, menu);
        return true;
    }

    // Response the activity which is selected by user
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.menu_flightinfo:
                intent = new Intent(this,FlightInfoActivity.class );
                startActivity(intent);
                result = true;
                break;
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
