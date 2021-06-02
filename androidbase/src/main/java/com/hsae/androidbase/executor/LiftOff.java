package com.hsae.androidbase.executor;

import android.util.Log;

import com.hsae.androidbase.logutils.logutils;

public class LiftOff implements Runnable{
    protected int countDown = 10; //Default
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "LiftOff!") + ") ";
    }
    @Override
    public void run() {
        while(countDown-- > 0) {
            logutils.d(status());
            Thread.yield();
        }

    }
}