package com.example.mytripplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestItemsServiceTask extends AsyncTask<Void,Void,FlightInfoResult> {

    private Context mContext = null;
    public  RequestItemsServiceTask(Context context){this.mContext = context;}

    @Override
    protected FlightInfoResult doInBackground(Void... unused) {
        FlightInfoResult result = null;
        Log.i("MyTripPlanner","In Call service");
        try{
            JSONArray serviceItems = WebServiceUtil.requestWebService(
                    "http://10.0.2.2:3000/root").getJSONArray("flighttime");
            result = new FlightInfoResult();

            ArrayList<HashMap<String, String>> resultData = new ArrayList<HashMap<String,String>>();
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

    @Override
    protected void onPostExecute(FlightInfoResult result) {
        ListDB db = new ListDB(mContext);
        ArrayList<HashMap<String, String>> resultData = result.getData();
        try {
            for(int i=0; i < resultData.size(); i++){
                String city = resultData.get(i).get("city");
                db.insertAirport(resultData.get(i).get("city"));
                String time = resultData.get(i).get("time");
                db.insertTime(resultData.get(i).get("time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
