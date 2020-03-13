/*
 *   NAME    : PerInfoActivity.java
 *   Project: Mobile Application Development - Assignment 2233
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The PerInfoActivity class has been created to provide a method for user input
 *             such as a user name and city of departure. The PerinfoActivity also has the
 *             ability to show a list of departure cities in a form of dropdown. When a city
 *             of departure is selected, the class shows a toast message of the selected city.
 *             In addition, it provides with a menu to jump to other screens.
 */

package com.example.mytripplanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;



public class PerInfoActivity extends Activity {

    private Spinner mSpinner = null;
    private ArrayAdapter<String> mSpinnerAdapter = null;
    String mDeparture;
    ListDB db = new ListDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perinfo);
        //Receive JSON from json server
        new RequestItemsServiceTask(this).execute();

        mSpinner = (Spinner) findViewById(R.id.spinner);



        mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                (String[])getResources().getStringArray(R.array.array_list));

        // In case of dropdown
        mSpinnerAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {             // in case of selecting a city
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                mDeparture = adapterView.getItemAtPosition(position).toString();

                Toast.makeText(PerInfoActivity.this, mDeparture+" selected",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });


        final EditText etName = findViewById(R.id.et_name);

        Button btn = findViewById(R.id.btn_perinfo);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       String name = etName.getText().toString();
                                       Data data = new Data(name,mDeparture,"","","");

                                       Intent intent = new Intent(PerInfoActivity.this,TripInfoActivity.class );
                                       intent.putExtra("data",data);                        // the user name

                                       startActivity(intent);
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
