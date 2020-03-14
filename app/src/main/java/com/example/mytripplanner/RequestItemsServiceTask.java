/*
 *   NAME    : RequestItemsServiceTask.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The RequestItemsServiceTask class has been created to use
 *             thread implemented by using AsyncTask during receiving json data
 *             from json server and after the doInBackground method finishes,
 *             store the data in SQLite Database.
 */

package com.example.mytripplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

// Threads are implemented by using AsyncTask class
// AsyncTask is not an independent thread. If you spawn multiple tasks,
// they will be placed in the queue and executed in succession.
// AsyncTask<p1,p2,p3>
// P1 - type of input parameters
// P2 - type of progress units published during background execution
// P3 - type of result used
public class RequestItemsServiceTask extends AsyncTask<Void,Void,FlightInfoResult> {

    private Context mContext = null;

    public  RequestItemsServiceTask(Context context){this.mContext = context; }


    //On the background thread immediately after the onPreExecute method finishes
    //An array of parameters is passed to this method. This method returns a result
    //pased to the onPostExecute method, it passes progress values to the onProgressUpdate
    //method
    @Override
    protected FlightInfoResult doInBackground(Void... unused) {
        FlightInfoResult result = null;
        Log.i("JsonServer","In Call service");
        try{
            //receive json data as json array from json server
            JSONArray serviceItems = WebServiceUtil.requestWebService(
                    "http://10.0.2.2:3000/root").getJSONArray("flighttime");
            result = new FlightInfoResult();

            ArrayList<HashMap<String, String>> resultData = new ArrayList<HashMap<String,String>>();
            // fetch data from hashmap using key
            for(int i=0; i < serviceItems.length(); i++){
                JSONObject obj = serviceItems.getJSONObject(i);
                HashMap<String,String> singleResult = new HashMap<String ,String>();
                singleResult.put("id", obj.getString("id"));
                singleResult.put("city", obj.getString("city"));
                singleResult.put("time", obj.getString("time"));
                resultData.add(singleResult);
            }
            result.setData(resultData);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // On the UI thread after the doInBackground method finishes.
    @Override
    protected void onPostExecute(FlightInfoResult result) {
        //Insert to Database
        ListDB db = new ListDB(mContext);
        ArrayList<HashMap<String, String>> resultData = result.getData();
        try {
            for(int i=0; i < resultData.size(); i++){
                db.insertAirport(resultData.get(i).get("city"));
                db.insertTime(resultData.get(i).get("time"));
            }

            db.insertTrip("OneWay");
            db.insertTrip("Round");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
