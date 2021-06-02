package com.hsae.reentrantlock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "mxzhq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Alipay alipay = new Alipay(10,100);


        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    alipay.transfer(7,1,30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                alipay.check();
            }
        };

        try {
            alipay.transfer(7,1,20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alipay.check();

        Log.d(TAG, "==================================================");

        thread.start();

        try {
            alipay.transfer(7,1,1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alipay.check();

        Log.d(TAG, "==================================================");
    }

    public  synchronized static void method(){

    }


}