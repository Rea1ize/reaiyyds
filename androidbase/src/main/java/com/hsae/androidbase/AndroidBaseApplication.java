package com.hsae.androidbase;

import android.app.Application;
import android.util.Log;


public class AndroidBaseApplication extends Application {
    final String TAG = "4444";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "AndroidBaseApplication onCreate");

    }



}
