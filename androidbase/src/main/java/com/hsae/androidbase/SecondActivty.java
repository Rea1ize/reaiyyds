package com.hsae.androidbase;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SecondActivty extends Activity {

   static final String TAG = "4444";
    private Process process;
    private DataOutputStream dataOutputStream;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private String state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // dowork("setprop odm.hsae.mmc.block /dev/block/mmcblk0");

        SystemProperties.set("odm.hsae.mmc.block","/dev/block/mmcblk0");
        SystemProperties.set("odm.hsae.mmc.command","do_get_recovery_status");
        SystemProperties.set("odm.hsae.mmc.state","1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        state  =  SystemProperties.get("odm.hsae.mmc.state");

        String result =  state.substring(47,51);
        Log.d(TAG, "state"+state);
        Log.d(TAG, "result: "+result);

        String progress =  state.substring(53,66);
        Log.d(TAG, "progress: "+progress);


    }

    public  void dowork(String order){

        try {
            process = Runtime.getRuntime().exec("sh");
            dataOutputStream = new DataOutputStream(process.getOutputStream());//input cmd
            inputStream = process.getInputStream();//output data

            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            dataOutputStream.writeBytes(order);
            dataOutputStream.flush();
         //   Thread.sleep(2000L);
            state = bufferedReader.readLine();
            Log.d(TAG, "state:"+state);
            inputStream.close();
            process.destroy();


//            dataOutputStream.writeBytes(catOrder);
//            dataOutputStream.flush();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
