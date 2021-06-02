package com.hsae.blockingqueue;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;

public class Block {

    private int queuesize  = 10;
    private ArrayBlockingQueue<Integer> queue  = new ArrayBlockingQueue<>(queuesize);
    final String TAG = "0128";

    class Consumer extends Thread{
        @Override
        public void run() {

            while (true){
                try {
                    queue.take();//当队列里面没有数据的时候这里会阻塞住
                    Log.d(TAG, "消费者取走一个数据: "+queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class Productor extends Thread{
        @Override
        public void run() {

            while (true){
                try {
                    queue.put(1);//数据满了也会阻塞住
                    Log.d(TAG, "生产者放了一个数据: "+queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void run() throws InterruptedException {
        Consumer consumer = new Consumer();
        Productor productor = new Productor();
        consumer.start();
        productor.start();
    }


}
