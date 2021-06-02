package com.hsae.androidbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hsae.androidbase.intentservice.LogcopyService;
import com.hsae.androidbase.remoteservice.SonService;
import com.hsae.androidbase.remoteservice.UncleService;

public class MainActivity extends AppCompatActivity {

    private Button Button;

    final String TAG = "4444";

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "androidbase: "+getApplicationContext());

        Button = findViewById(R.id.bt_stop);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),SonService.class);
                intent1.setAction("android.intent.action.SonService");
                stopService(intent1);

                Intent intent2 = new Intent(getApplicationContext(), UncleService.class);
                intent2.setAction("android.intent.action.UncleService");
                intent2.setType("unbind");
                startService(intent2);
            }
        });


        Intent intent = new Intent(this, LogcopyService.class);
        startService(intent);
        Log.d("4444", "onCreate: "+ "Thread: "+Thread.currentThread().getName());

        Intent intent1 = new Intent(this, SonService.class);
        intent1.setAction("android.intent.action.SonService");
      //  startService(intent1);//多次start会触发多次start和startcommand


        Intent intent2 = new Intent(this, UncleService.class);
        intent2.setAction("android.intent.action.UncleService");
        intent2.setType("bind");
        startService(intent2);
    }
}