package com.hsae.androidbase.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main() {
        //三个线程来执行五个任务
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }

}
