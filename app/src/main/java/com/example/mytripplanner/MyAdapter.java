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

    @Override
    public int getCount(){
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position){
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_list_item, parent, false);
        }

        TextView user_name = (TextView) convertView.findViewById(R.id.userId);
        TextView departure_name = (TextView) convertView.findViewById(R.id.departureId);
        TextView destination_name = (TextView) convertView.findViewById(R.id.destinationId);
        TextView adult_num = (TextView) convertView.findViewById(R.id.adultNum);
        TextView child_num = (TextView) convertView.findViewById(R.id.childNum);
        TextView trip_type = (TextView) convertView.findViewById(R.id.tripType);

        MyItem myItem = getItem(position);

        user_name.setText(myItem.getName());
        departure_name.setText(myItem.getDeparture());
        destination_name.setText(myItem.getDestination());
        adult_num.setText(myItem.getAdult());
        child_num.setText(myItem.getChild());
        trip_type.setText(myItem.getTrip());

        return convertView;
    }

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
