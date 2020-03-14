/*
 *   NAME    : WebServiceUtil.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The WebServiceUtil class has been created to provide a method for creating
 *             and changing a table and schema.
 */

package com.example.mytripplanner;

import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class WebServiceUtil {

    public  static JSONObject requestWebService(String serviceUrl){
        disableConnectionResueIfNecessary();

        HttpURLConnection urlConnection = null;
        try{
            //create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();

            //handle issues
            int statusCode = urlConnection.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_UNAUTHORIZED){
                // handle unauthorized (if service requires user login)
            } else if(statusCode != HttpURLConnection.HTTP_OK){
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new JSONObject(getResponseText(in));

        } catch (MalformedURLException e) {
            // URL is invalid
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
            e.printStackTrace();
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return  null;
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private  static  void disableConnectionResueIfNecessary() {
        // see HttpURLConnection API doc
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive","false");
        }
    }

    private static  String  getResponseText(InputStream inStream){
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
