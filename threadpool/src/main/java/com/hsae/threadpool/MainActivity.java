package com.hsae.threadpool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private HandlerThread handlerThread;

    final String TAG = "reai";

    private Handler mHandler;

    private Handler mHandler1;

    private Handler mHandler2;

    private Looper mLooper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        handlerThread = new HandlerThread("我热爱的-v-");
        handlerThread.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: "+Thread.currentThread());
                Looper.prepare();
            }
        };
//        mHandler = new Handler(Looper.myLooper()){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                switch (msg.what){
//                    case 1:
//                        Log.d(TAG, "handleMessage: 1");
//                        break;
//                }
//            }
//        };


        new Thread(){
            @Override
            public void run() {
                mHandler = new Handler(handlerThread.getLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 1:
                                Log.d(TAG, "handleMessage: "+msg.what + "Thread" +Thread.currentThread().getName());
                                break;
                            case 2:
                                Log.d(TAG, "handleMessage: "+msg.what  + "Thread" +Thread.currentThread().getName());
                                break;
                        }
                    }
                };

            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                mHandler1 = new Handler(handlerThread.getLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 1:
                                Log.d(TAG, "handleMessage1: "+msg.what + "Thread" +Thread.currentThread().getName());
                                break;
                            case 2:
                                Log.d(TAG, "handleMessage1: "+msg.what  + "Thread" +Thread.currentThread().getName());
                                break;
                        }
                    }
                };

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                mHandler2 = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 1:
                                Log.d(TAG, "handleMessage2: "+msg.what + "Thread" +Thread.currentThread().getName());
                                break;
                            case 2:
                                Log.d(TAG, "handleMessage2: "+msg.what  + "Thread" +Thread.currentThread().getName());
                                break;
                        }
                    }
                };
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mHandler.obtainMessage(1).sendToTarget();
        mHandler1.obtainMessage(1).sendToTarget();
        mHandler2.obtainMessage(1).sendToTarget();

        mHandler.obtainMessage(2).sendToTarget();
        mHandler1.obtainMessage(2).sendToTarget();
        mHandler2.obtainMessage(2).sendToTarget();

//        Thread mThread = new Thread(){
//            @Override
//            public void run() {
//                Log.d(TAG, "run: "+Thread.currentThread());
//                Looper.prepare();
//            }
//        };
//
//        mThread.run();


       // new Thread(runnable).run();

        //handlerThread.run();





        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();


    }
}