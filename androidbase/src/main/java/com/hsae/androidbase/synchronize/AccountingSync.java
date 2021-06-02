package com.hsae.androidbase.synchronize;

import android.util.Log;

import com.hsae.androidbase.logutils.logutils;

public class AccountingSync implements Runnable{
    //共享资源(临界资源)
    static int i=0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void  increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }

    public static void main() throws InterruptedException {
        AccountingSync instance=new AccountingSync();
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);

        //new新实例
       // Thread t1=new Thread(new AccountingSyncBad());这里就是2个同的对象锁，除非increase用static修饰
        //new新实例
        //Thread t2=new Thread(new AccountingSyncBad());


        t1.start();
        t2.start();
        t1.join();
        t2.join();
        logutils.d(""+i);
    }
    /**
     * 输出结果:
     * 2000000
     */

}
