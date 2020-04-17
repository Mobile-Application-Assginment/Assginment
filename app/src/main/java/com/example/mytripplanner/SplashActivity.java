/*
 *   NAME    : SplashActivity.java
 *   Project: Mobile Application Development - Assignment 2
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Mar. 14, 2020
 *   PURPOSE : The SplashActivity class has been created to Use Splash image
 *             and start thread to receive json data from json server
 */

package com.example.mytripplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Receive JSON from json server - start the task
        new RequestItemsServiceTask(this).execute();

        //Create an intent to transport splash screen to first screen
        Intent intent = new Intent(this, PerInfoActivity.class);
        startActivity(intent);

        finish();
    }
}
