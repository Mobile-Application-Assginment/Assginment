package com.example.mytripplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestItemServiceTask extends AsyncTask<Void,Void,String> {

    private Context mContext = null;
    public static String strTTS; //cgl
    public  RequestItemServiceTask(Context context){this.mContext = context;}

    @Override
    protected String doInBackground(Void... unused) {
        Log.i("MyTripPlanner","In Call service");
        try{
            Log.i("TTS","downloading json file for TTS");  //cgl
            JSONArray serviceItems2 = WebServiceUtil.requestWebService("http://10.0.2.2:3001/root").getJSONArray("TTS"); //cgl
            JSONObject obj2 = serviceItems2.getJSONObject(0); //cgl
            strTTS = (String)obj2.getString("welcome"); //cgl
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strTTS;
    }

    @Override
    protected void onPostExecute(String result) {  //cgl remove
        ListDB db = new ListDB(mContext); 
    }
}
