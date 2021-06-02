package com.hsae.blockingqueue;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class ObjectLock extends Activity {
    private Object object = new Object();
    private Object1 object1 = new Object1();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    object1.test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    object1.test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread3 = new Thread(){
            @Override
            public void run() {
                try {
                    object1.test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread4 = new Thread(){
            @Override
            public void run() {
                try {
                    object1.testNotify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public class Object1 {

        public void test() throws InterruptedException {

            synchronized (this){
                Log.d("mxzhq", "test: 拿到object锁" + " ThreadName "+Thread.currentThread().getName());
                Thread.sleep(2000);
                object1.wait();
                Log.d("mxzhq", "test: 我已被唤醒" + " ThreadName "+Thread.currentThread().getName());
            }

        }

        public void testNotify() throws InterruptedException {
            synchronized (this){
                notifyAll();
                Log.d("mxzhq", "test: 唤醒等待线程");
            }
        }

    }






    public class Object {

        public void  testNotify1() throws InterruptedException {
            synchronized (this) {
                notifyAll();
                Log.d("mxzhq", "test: 唤醒等待线程");
            }
        }

    }

}
