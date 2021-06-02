package com.hsae.androidbase.blockingqueue;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 等待唤醒机制与synchronized
 * 所谓等待唤醒机制本篇主要指的是notify/notifyAll和wait方法，在使用这3个方法时，必须处于synchronized代码块或者synchronized方法中，
 * 否则就会抛出IllegalMonitorStateException异常，这是因为调用这几个方法前必须拿到当前对象的监视器monitor对象，
 * 也就是说notify/notifyAll和wait方法依赖于monitor对象，在前面的分析中，我们知道monitor 存在于对象头的Mark Word 中(存储monitor引用指针)，
 * 而synchronized关键字可以获取 monitor ，这也就是为什么notify/notifyAll和wait方法必须在synchronized代码块或者synchronized方法调用的原因。
 *
 * synchronized (obj) {
 *        obj.wait();
 *        obj.notify();
 *        obj.notifyAll();
 *  }
 * ————————————————
 * 版权声明：本文为CSDN博主「zejian_」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/javazejian/article/details/72828483
 *
 * monitor对象存在于每个Java对象的对象头中(存储的指针的指向)，synchronized锁便是通过这种方式获取锁的
 * 当一个 monitor 被某个线程持有后，它便处于锁定状态
 * monitor对象会记录释放锁的线程和持有锁的线程
 *
 */
public class TestTcuActivity extends Activity {
    private final String TAG = TestTcuActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThreadB b = new ThreadB();
        b.start();
        Log.d(TAG, "b is start....");
        /**
         * 意思是定义一个同步块,使用b作为资源锁。
         * b.wait();的意思是临时释放锁，并阻塞当前线程,
         * 好让其他使用同一把锁的线程有机会执行,
         * 在这里要用同一把锁的就是b线程本身.
         * 这个线程在执行到一定地方后用notify()通知wait的线程,锁已经用完,
         * 待notify()所在的同步块运行完之后,
         * wait所在的线程就可以继续执行
         */
        synchronized (b)//括号里的b是什么意思,起什么作用?
        {
            try {
                Log.d(TAG, "Waiting for b to complete...");
                b.wait();//这一句是什么意思，究竟让谁wait?让主线程 main wait
                Log.d(TAG, "Completed.Now back to main thread");
            } catch (InterruptedException e) {

            }
        }
        Log.d(TAG, "Total is :" +b.total);
    }


    class ThreadB extends Thread {
        int total;

        @Override
        public void run() {

            synchronized (this) {
                Log.d(TAG, "ThreadB is running..");
                for (int i = 0; i < 10; i++) {
                    total += i;
                    Log.d(TAG, "ThreadB total is "+ total);
                }
                notify();
            }

        }
    }

}
