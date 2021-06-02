package com.hsae.androidbase.handlerthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hsae.androidbase.logutils.logutils;

public class HandlerActivity extends Activity {

    private Handler mhanler ;


    private Handler.Callback mcallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    logutils.d("111主线程发的"+Thread.currentThread().getName());
                    break;
                case 2:
                    logutils.d("111子线程发的2"+Thread.currentThread().getName());
                    break;
            }
            return false;


        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                mhanler = new Handler(Looper.myLooper(), mcallback) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 1:
                                logutils.d("主线程发的1"+Thread.currentThread().getName());
                                break;
                            case 2:
                                logutils.d("子线程发的2");
                                break;
                        }
                    }
                };

                mhanler.sendEmptyMessageDelayed(2,1000);
                Looper.loop();
                mhanler.sendEmptyMessageDelayed(2,1000);
            }
        }).start();



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mhanler.sendEmptyMessageDelayed(1,1000);

      //  mhanler.sendEmptyMessageDelayed(2,1000);

    }







}



