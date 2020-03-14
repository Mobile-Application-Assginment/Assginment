/*
 *   NAME    : ConfirmInfoActivity.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
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
import java.util.ArrayList;

public class ConfirmInfoActivity extends Activity {

    // The properties
    String custName = "";
    String departure = "";
    String destination = "";
    String adultNum = "0";
    String childNum = "0";
    ListDB db = new ListDB(this);


    // Create the activity
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
        // Get each value from intent
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



        Button btn = findViewById(R.id.btn_flight);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Uri webpage = Uri.parse("https://flightaware.com/live/airport/delays");
                                       Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                                       if (intent.resolveActivity(getPackageManager()) != null) {
                                           startActivity(intent);
                                       }
                                   }
                               }
        );
    }


    public ArrayList<MyItem> getMyItemList (ArrayList<Task> tasks) {
        int taskSize = tasks.size();

        ArrayList<MyItem> myItems = new ArrayList<>();

        for(int i = 0; i < taskSize; i++)
        {
            Task task = tasks.get(i);

            MyItem myitem = new MyItem(db.getUserName((int)task.getUserId()),db.getAirportName((int)task.getDepatureAirportId()),
                    db.getAirportName((int)task.getDestinationAirportId()), db.getTimeValue((int)task.getTimeId()),
                    Integer.toString(task.getAdultNum()), Integer.toString(task.getChildNum()), db.getTripType(task.getTripId()));

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
