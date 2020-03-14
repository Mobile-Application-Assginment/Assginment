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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class ConfirmInfoActivity extends Activity {
    String custName = "";
    String departure = "";
    String destination = "";
    String adultNum = "0";
    String childNum = "0";
    ListDB db = new ListDB(this);

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirminfo);

        // Receive data from Tripinfo activity throw intent
        Intent intent_receive = getIntent();
        Data data_receive = (Data) intent_receive.getSerializableExtra("data");

        // Get the each elements ID
//        TextView customerName = findViewById(R.id.user_name);
//        TextView numberAdult = findViewById(R.id.adult_num);
//        TextView numberChild = findViewById(R.id.child_num);
//        TextView departureCity = findViewById(R.id.depature_result);
//        TextView detinationCity = findViewById(R.id.detination_result);
//
//        // Get each value from intent
        custName = data_receive.name;
        departure = data_receive.departure;
        destination = data_receive.destination;
        adultNum = data_receive.adultNumber;
        childNum = data_receive.childNumber;

        ArrayList<Task> tasks = db.getTaskList((int)db.getUserId(custName));
        ArrayList<MyItem> myItems = getMyItemList(tasks);

        ListView myList = (ListView)findViewById(R.id.result_list);

        MyAdapter myAdapter = new MyAdapter();
        for(int i = 0; i < myItems.size(); i++)
        {
            MyItem myItem = myItems.get(i);
            myAdapter.addItem(myItem.getName(), myItem.getDeparture(), myItem.getDestination(),
                    myItem.getAdult(), myItem.getChild(), myItem.getTrip());
        }
        myList.setAdapter(myAdapter);


//
//        // Print the value into the XML element
//        customerName.setText(custName);
//        departureCity.setText(departure);
//        detinationCity.setText(destination);
//        numberAdult.setText(adultNum);
//        numberChild.setText(childNum);

//        Button btn = findViewById(R.id.btn_call);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//                                   @Override
//                                   public void onClick(View v) {
//                                       Uri webpage = Uri.parse("https://flightaware.com/live/airport/delays");
//                                       Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                                       if (intent.resolveActivity(getPackageManager()) != null) {
//                                           startActivity(intent);
//                                       }
//                                   }
//                               }
//        );





//        String[] columns = {/*ListDB.TASK_ID,*/ ListDB.TASK_USER_ID, ListDB.TASK_DEPARTURE_AIRPORT_ID,
//                ListDB.TASK_DESTINATION_AIRPORT_ID, /*ListDB.TASK_TIME_ID,*/ ListDB.TASK_ADULT_NUM, ListDB.TASK_CHILD_NUM,};
//        int[] to = {/*R.id.taskId,*/ R.id.userId, R.id.departureId,
//                R.id.destinationId, /*R.id.timeId,*/ R.id.adultNum, R.id.childNum};
//
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
//                this, R.layout.task_list_item,
//                db.getTaskCursor((int)db.getUserId(custName)),
//                columns, to, 0
//        );
//        ListView myList = (ListView)findViewById(R.id.result_list);
//        myList.setAdapter(adapter);
    }


    public ArrayList<MyItem> getMyItemList (ArrayList<Task> tasks) {
        int taskSize = tasks.size();

        ArrayList<MyItem> myItems = new ArrayList<>();

        for(int i = 0; i < taskSize; i++)
        {
            Task task = tasks.get(i);

            MyItem myitem = new MyItem(db.getUserName((int)task.getUserId()),db.getAirportName((int)task.getDepatureAirportId()),
                    db.getAirportName((int)task.getDestinationAirportId()), db.getTimeValue((int)task.getTimeId()), db.getTripType(task.getTripId()),
                    Integer.toString(task.getAdultNum()), Integer.toString(task.getChildNum()));

            myItems.add(myitem);
        }

        return myItems;
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
