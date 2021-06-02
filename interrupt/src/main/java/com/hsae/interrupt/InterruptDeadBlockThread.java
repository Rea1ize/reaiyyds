package com.hsae.interrupt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 中断处于死锁状态的两个线程，但这两个线都没有收到任何中断信号（抛出异常），所以interrupt()方法是不能中断死锁线程的，因为锁定的位置根本无法抛出异常：
 * 自己理解:线程死锁了，自然没法去检测中断标志位，所以会一直锁住
 */
public class InterruptDeadBlockThread extends Activity {

   static final String TAG = "mxzhq";

    final Object lock1 = new Object();
    final Object lock2 = new Object();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                super.run();
                deathLock(lock1,lock2);
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                super.run();
                deathLock(lock2,lock1);
            }
        };

        Log.d(TAG, "Starting thread...");

        thread1.start();//假设等thread1执行完后，在启动thread2,这样就不会死锁
        thread2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Interrupting thread...");
        thread1.interrupt();
        thread2.interrupt();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Stopping application...");

        
    }


    static void deathLock(Object lock1,Object lock2){
            
        synchronized (lock1){
            try {
                Thread.sleep(10);//不会在这里死掉
                synchronized (lock2){//会锁在这里，虽然阻塞了，但不会抛异常
                    Log.d(TAG, "deathLock: "+Thread.currentThread());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.d(TAG, "InterruptedException...");
                System.exit(1);
            }
        }
    }

}
