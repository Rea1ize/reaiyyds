package com.hsae.blockingqueue;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lanyou.tcu.protocolservicelibrary.ITcuDeviceManager;
import com.lanyou.tcu.protocolservicelibrary.ServiceProxy;

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
                for (int i = 0; i < 100; i++) {
                    total += i;
                    Log.d(TAG, "ThreadB total is "+ total);
                }
                notify();
            }

        }
    }


}
