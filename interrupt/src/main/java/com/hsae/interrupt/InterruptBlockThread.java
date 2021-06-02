package com.hsae.interrupt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
/**
  中断阻塞状态线程
  自己理解:由此可见阻塞线程还是可以去检测中断标志位的。中断阻塞线程会抛出异常，中断非阻塞线程不会
 */
public class InterruptBlockThread extends Activity {
    
    String TAG ="mxzhq";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            doTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doTest() throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        Log.d(TAG, "Thread running...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Thread is interrupted...");
                        Thread.currentThread().interrupt();//抛出中断异常后，中断标志位会重置，所以要重新设置中断状态为ture
                    }
                }
            }
        };

        Thread testThread = new Thread(runnable);
        testThread.start();

        Thread.sleep(3000);
        testThread.interrupt();//中断阻塞线程，抛出异常，线程退出阻塞状态
        Thread.sleep(1000);

        Log.d(TAG, "testThread.isAlive: "+testThread.isAlive());
    }


}
