//
//  NAME        : MyAdapter.java
//  Project     : Mobile Application Development - Assignment 2
//  By          : Charng Gwon Lee, Hyungbum Kim, Younchul Cho
//  Date        : Mar. 14, 2020
//  PURPOSE     : The MyAdapter class has been created to use adapter
//                  for a listview and spinner
//

package com.example.mytripplanner;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<MyItem> mItems = new ArrayList<>();

    // getter for count number
    @Override
    public int getCount(){
        return mItems.size();
    }
    // getter for position of item in arraylist
    @Override
    public MyItem getItem(int position){
        return mItems.get(position);
    }
    // getter for id of item
    @Override
    public long getItemId(int position){
        return 0;
    }
    // getter for view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            Context context = parent.getContext();

            // use inflater to make view defined with XML.
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.task_list_item, parent, false);
            }

            // fill in the variable to fill in the data in view which is made from inflater
            TextView user_name = (TextView) convertView.findViewById(R.id.userId);
            TextView departure_name = (TextView) convertView.findViewById(R.id.departureId);
            TextView destination_name = (TextView) convertView.findViewById(R.id.destinationId);
            TextView adult_num = (TextView) convertView.findViewById(R.id.adultNum);
            TextView child_num = (TextView) convertView.findViewById(R.id.childNum);
            TextView trip_type = (TextView) convertView.findViewById(R.id.tripType);

            MyItem myItem = getItem(position);
            // fill in the data in view which is made from inflater
            user_name.setText(myItem.getName());
            departure_name.setText(myItem.getDeparture());
            destination_name.setText(myItem.getDestination());
            adult_num.setText(myItem.getAdult());
            child_num.setText(myItem.getChild());
            trip_type.setText(myItem.getTrip());

            return convertView;
    }

    // add trip info to item object which is made to store trip data
    public void addItem(String user, String departure, String destination, String adult, String child, String trip) {

        if(user != null)
        {
            MyItem mItem = new MyItem();

            mItem.setName(user);
            mItem.setDeparture("Departure City: " + departure);
            mItem.setDestination("Destination City: " + destination);
            mItem.setAdult("Number of Adult: " + adult);
            mItem.setChild("Number of Child: " + child);
            mItem.setTrip("Type of Trip: " + trip);

            mItems.add(mItem);
        }

    }
}
