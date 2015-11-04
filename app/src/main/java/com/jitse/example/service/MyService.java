package com.jitse.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jitse on 8/29/14.
 */
public class MyService extends Service {

    private static final String TAG = MyService.class.getName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful

        Log.d(TAG, "onStartCommand");


        Intent newIntent = new Intent("asdf");
        newIntent.putExtra("word", "1");
        newIntent.putExtra("word2", "2343");
        sendBroadcast(newIntent);

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }
}