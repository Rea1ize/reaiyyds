package com.hsae.androidbase.handlerthread;

import android.os.HandlerThread;
import android.os.Looper;

public class MyHandlerThread extends HandlerThread {


    public MyHandlerThread(String name) {
        super(name);
    }

    @Override
    public int getThreadId() {
        return super.getThreadId();
    }


    @Override
    public void run() {
        super.run();

    }
}
