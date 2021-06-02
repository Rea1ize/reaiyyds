package com.hsae.reentrantlock;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alipay {
    final static String TAG = "mxzhq";
    private double[] accounts;
    private Lock alipaylock;
    private Condition condition;

    public Alipay(int n,double money){
        accounts = new double[n];
        alipaylock = new ReentrantLock();
        //得到条件对象
        condition=alipaylock.newCondition();
        for (int i =0;i<accounts.length;i++){
            accounts[i] = money;
        }
    }

    public void transfer(int from,int to,int amount) throws InterruptedException {
        Log.d(TAG, "transfer: from: "+from+" to: "+to+" amount: "+amount);
        alipaylock.lock();
        Log.d(TAG, "alipaylock.lock");
        try {
            while (accounts[from]<amount){
             //   Log.d(TAG, "transfer: 主线程阻塞");
                //阻塞当前线程，并放弃锁
               Log.d(TAG, "condition.await");
               condition.await();  //如果这里不释放锁，子线程将无法执行aliplaylock.lock()方法
            }
            //转账操作
            accounts[from] = accounts[from] - amount;
            accounts[to] = accounts[to]+amount;
            Log.d(TAG, "condition.signalAll");
            condition.signalAll();
        } finally {
            alipaylock.unlock();//如果临界区发生了异常，锁是不需要释放的，否则其他线程将永远被阻塞
            Log.d(TAG, "alipaylock.unlock");
        }
    }

    public void check(){
        for (int i = 0;i<accounts.length;i++){
            Log.d(TAG, " check: "+i+" money "+accounts[i]);
        }
    }

    public static void learn(){
        Log.d(TAG, "learn: ");
    }

}
