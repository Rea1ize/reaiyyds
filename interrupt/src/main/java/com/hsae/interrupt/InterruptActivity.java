package com.hsae.interrupt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 中断非阻塞状态线程
 */
public class InterruptActivity extends Activity {

    volatile boolean state ;     //使用共享变量中断线程

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while (!state){
                    long time = System.currentTimeMillis();
                    //使用while循环模拟sleep
                    Log.d("mxzhq", "1s");
                    while ((System.currentTimeMillis() - time) < 1000){

                    }
                }
                Log.d("mxzhq", "testthread will die");

                return;
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {

                while (!Thread.currentThread().isInterrupted()){
                    long time = System.currentTimeMillis();
                    //使用while循环模拟sleep
                    Log.d("mxzhq", "1s");
                    while ((System.currentTimeMillis() - time) < 1000){

                    }
                }

                Log.d("mxzhq", "testthread will die");

                return;

            }
        };

        Thread testThread = new Thread(runnable1);//使用interrupt中断子线程
        //Thread testThread = new Thread(runnable);//全局变量中断线程
        testThread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        testThread.interrupt();//使用interrupt中断子线程

        //state = true; //全局变量中断线程

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("mxzhq", "testthread: "+testThread.isAlive());//runnable执行完后销毁，无人持有testthread,testthread被回收



    }



}
