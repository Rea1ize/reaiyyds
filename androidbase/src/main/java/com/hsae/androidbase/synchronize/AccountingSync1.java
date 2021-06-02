package com.hsae.androidbase.synchronize;


import com.hsae.androidbase.logutils.logutils;

/***
 * synchronized的可重入性
 */
public class AccountingSync1 implements Runnable{

    static AccountingSync1 instance=new AccountingSync1();
    static int i=0;
    static int j=0;
    @Override
    public void run() {
        for(int j=0;j<100;j++){
            logutils.d("run");
            //this,当前实例对象锁
            synchronized(this){
                i++;
                increase();//synchronized的可重入性,一个线程得到一个对象锁后再次请求该对象锁，是允许的，这就是synchronized的可重入性
//                try {
//                    this.wait();// this就是内部锁
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public synchronized void increase(){
        logutils.d("increase");
        j++;
    }

    public static void main() throws InterruptedException {
        Thread t1=new Thread(instance);
         Thread t2=new Thread(instance);
        t1.start();
       // t2.start();
        t1.join();
       // t2.join();
        logutils.d("AccountingSync1: "+j);
    }

}
