package com.hsae.androidbase.synchronize;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class SyncActivity extends Activity {

    private static final byte[] mStaticLockByte = new byte[1];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        try {
//            AccountingSync.main();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        try {
            AccountingSync1.main();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    //静态方法，使用的是Class类锁
    synchronized public static void staticMethod() {}

   // 静态方法代码块1，使用的是Class类锁
    public static void staticBlock1() {
        synchronized (SyncActivity.class) {}
    }

   // 静态方法代码块2，使用的是内部静态变量锁
    public static void staticBlock2() {
        synchronized (mStaticLockByte) {}
    }

    //普通方法，使用的是调用该方法的对象锁
    synchronized public void method() {}


}
