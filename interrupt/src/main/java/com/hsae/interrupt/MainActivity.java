package com.hsae.interrupt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.locks.ReentrantLock;

/**
 * //中断线程（实例方法）
 * public void Thread.interrupt();
 *
 * //判断线程是否被中断（实例方法）
 * public boolean Thread.isInterrupted();
 */
public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();


        class TestThread extends Thread {
            @Override
            public void run() {
                super.run();
                long id = Thread.currentThread().getId();

                synchronized (TestThread.class){
                    Log.d("testthread", "run: synchronized");
                    for (int i = 0 ; i<999; i++){
                        Thread.currentThread().interrupt();
                    }
                }
               // ReentrantLock.lockInterruptibly();


                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

               // Thread.currentThread().interrupt();
                //boolean state = Thread.currentThread().isInterrupted();

               // TestThread.interrupted();
                boolean state = Thread.currentThread().isInterrupted();

                Log.d("testthread", " run: id "+id+" state "+state);
            }
        }

        TestThread testThread = new TestThread();
        testThread.start();
       // testThread.interrupt(); //线程在阻塞过程中，调用interrupt，会抛出interruptexception.

        new Thread(new Runnable() {

            @Override
            public void run() {
                boolean state = Thread.currentThread().isInterrupted();
                long id = Thread.currentThread().getId();
                Log.d("new thread", " run: id "+id+" state "+state);
            }

        }).start();


    }



}