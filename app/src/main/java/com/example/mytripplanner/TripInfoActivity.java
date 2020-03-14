/*
 *   NAME    : TripInfoActivity.java
 *   Project: Mobile Application Development - Assignment 1
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Feb. 7, 2020
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
    static Integer[] LIST_PERSONNUM = {0, 1, 2, 3, 4};
    String custName = "";
    String departure = "";
    String destination = "";
    String adultNum = "0";
    String childNum = "0";
    String tripType = "OneWay";

    //DB connect declare
//    String dbName = "sch_file.db";   // schedule Database
//    int dbVersion = 3;
//    private MySQLiteOpenHelper helper;
//    private SQLiteDatabase db;
//    String tag = "SQLite";    // tag for Log
//    String tableName = "schedule";  // table name of Database
    //DB connect declare end

    ListDB db = new ListDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripinfo);

        // Receive data from Perinfo activity throw intent
        Intent intent_receive = getIntent();
        Data data_receive = (Data) intent_receive.getSerializableExtra("data");

        TextView txtName = findViewById(R.id.txt_name);

        final TextView txtDeparture = findViewById(R.id.txt_departure);
        final TextView txtDestination = findViewById(R.id.txt_destination);

        custName = data_receive.name;
        txtName.setText("CUSTOMER: " + custName);
        departure = data_receive.departure;
        txtDeparture.setText(departure);

        // Add Radio button
        //RadioGroup rgTripType = findViewById(R.id.rg_tripType);


        //Use json file start
//        ArrayList<String> items = new ArrayList<String>();
//        try {
//            AssetManager assetManager = getResources().getAssets();
//            InputStream is = null;
//            byte buf[] = new byte[4096];
//            String str = "";
//            //open json file
//            try {
//                is = assetManager.open("location_time.json");
//                if (is.read(buf) > 0) {
//                    str = new String(buf);
//                }
//                is.close();
//            } catch (Exception e) {
//                Log.e("exception", "file exception", e);
//            }
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (Exception e) {
//                    Log.e("exception", "file exception", e);
//                }
//            }
//            JSONArray jarray = new JSONArray(str);
//
//            for(int i=0; i<jarray.length();i++){
//                JSONObject jObject = jarray.getJSONObject(i);
//                String location = jObject.getString("name");
//                items.add(location);
//            }
//
//        }catch (Exception e){
//            Log.e("exception","file exception",e);
//        }
        //Use json file end

        // Using listbox Adapter to display destination data
        //String[] datas = getResources().getStringArray(R.array.array_list);
        //ArrayAdapter listboxAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, datas);
        ArrayAdapter listboxAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, db.getAirportList());

        ListView listView = findViewById(R.id.list_schedule);
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

        // DB  Connect
//        helper = new MySQLiteOpenHelper(this, dbName, null, dbVersion);
//        try {
//            db = helper.getWritableDatabase();
//        } catch (SQLException e){
//            e.printStackTrace();
//            Log.e(tag,"Cannot open Database.");
//            finish();
//        }
        // DB Connect end

        // Event handler for confirm button click - move to confirminfoactivity screen
        Button btn = findViewById(R.id.btn_confirm);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       //DB insert
                                       //insert(custName,departure,destination,adultNum,childNum);
                                       Task task = new Task();
                                       task.setDepartureAirportId(db.getAirportId(departure));
                                       task.setDestinationAirportId(db.getAirportId(destination));
                                       task.setUserId(db.getUserId(custName));
                                       task.setAdultNum(Integer.parseInt(adultNum));
                                       task.setChildNum(Integer.parseInt(childNum));
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

    //DB CRUD
//    void insert (String custName, String departure, String destination, String adultNum, String childNum){
//        ContentValues values = new ContentValues();
//        // key-value pair
//        values.put("name", custName);
//        values.put("departure",departure);
//        values.put("destination", destination);
//        values.put("adultNumber",adultNum);
//        values.put("childNumber",childNum);
//        long result = db.insert(tableName,null,values);
//        Log.d(tag,result + "row inserted");
//    }
    //DB CRUD end
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
