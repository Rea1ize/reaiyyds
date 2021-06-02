package com.hsae.androidbase.nativeservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hsae.androidbase.R;

public class BindNativeActivity extends Activity {

    final String TAG = "4444";

    private NativeService.MyBinder myBinder;

    private ServiceConnection serviceConnection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            myBinder = (NativeService.MyBinder) service;
            String msg = myBinder.sendMessage("我热爱的");
            Log.d(TAG, "msg: "+msg);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,NativeService.class);
        boolean result = bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        Log.d(TAG, "BindNativeActivity bind nativeservice" + result);
    }



}
