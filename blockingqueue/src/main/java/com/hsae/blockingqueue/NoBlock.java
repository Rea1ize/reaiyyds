package com.hsae.blockingqueue;

import android.content.Intent;
import android.util.Log;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class NoBlock {
    private final String TAG = "0127生产者";
    private final String TBG = "0127消费者";
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    class Consumer extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                Log.d(TBG, "目前剩余总数: "+queue.size());
                synchronized (queue){
                    while (queue.size() == 0){
                        try {
                            Log.d(TAG, "队列空，等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //每次移走队首元素
                    queue.poll();
                    queue.notify();
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                synchronized (queue){
                    Log.d(TAG, "目前生产总数: "+queue.size());
                    while (queue.size() == queueSize){
                        try {
                            Log.d(TAG, "队列满，等待有空余空间");
                            queue.wait();//释放锁不在执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);
                    queue.notify();
                }
            }
        }
    }

    public  void run(){
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
    }

}
