package com.example.mytripplanner;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BackgroundMusicService extends Service {

    private static final String TAG = "BackgroudMusicService";
    MediaPlayer bgmPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Create");
        bgmPlayer = MediaPlayer.create(this, R.raw.bgmusic);
        bgmPlayer.setLooping(false);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Stop Backgroud Music service", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Destroy");
        bgmPlayer.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Start Backgroud Music Service",Toast.LENGTH_LONG).show();
        Log.d(TAG,"Start");
        bgmPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
}
