/*
 *   NAME    : BackgroundMusicService.java
 *   Project: Mobile Application Development - Assignment 3
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: Apr. 17, 2020
 *   PURPOSE : The BackgroundMusicService class has been created to run service
 *             for background music. Users can turn on and off music at first page
 */

package com.example.mytripplanner;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BackgroundMusicService extends Service {

    private static final String TAG = "BackgroundMusicService";
    MediaPlayer bgmPlayer;

    //	Name	: onCreate
    //	Purpose : Create service, get music file from raw folder in resource folder
    //            and play music
    @Override
    public void onCreate() {
        Log.d(TAG, "Create");
        bgmPlayer = MediaPlayer.create(this, R.raw.bgmusic);
        bgmPlayer.setLooping(false);
    }

    //	Name	: onCreate
    //	Purpose : stop service when push stop bgm button in personinfo activity
    @Override
    public void onDestroy() {
        Toast.makeText(this, "Stop Background Music service", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Destroy");
        bgmPlayer.stop();
    }

    //	Name	: onCreate
    //	Purpose : start service when push stop bgm button in personinfo activity
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Start Background Music Service",Toast.LENGTH_LONG).show();
        Log.d(TAG,"Start");
        bgmPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }

    //	Name	: onBind
    //	Purpose : Display information for appwidget from database
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
