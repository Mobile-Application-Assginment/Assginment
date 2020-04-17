/*
 *   NAME    : RequestTTSServiceTask.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The RequestTTSServiceTask class has been created to use
 *             thread implemented by using AsyncTask during receiving json data
  */
package com.example.mytripplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestTTSServiceTask extends AsyncTask<Void,Void,String> {

    private Context mContext = null;
    public static String strTTS;                                    // for Text-to-Speech
    public RequestTTSServiceTask(Context context){this.mContext = context;}

    @Override
    protected String doInBackground(Void... unused)
    {
        Log.i("MyTripPlanner","In Call service");

        try
        {
            Log.i("TTS","downloading json file for TTS");
            JSONArray serviceItems2 = JSONServiceUtil.requestWebService("http://10.0.2.2:3001/root").getJSONArray("TTS");
            JSONObject obj2 = serviceItems2.getJSONObject(0);
            strTTS = (String)obj2.getString("welcome");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return strTTS;
    }
}
