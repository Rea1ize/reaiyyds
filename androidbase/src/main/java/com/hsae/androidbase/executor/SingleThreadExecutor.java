package com.hsae.androidbase.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

    public static void main() {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 2; i++) {
            exec.execute(new LiftOff());
        }
    }

}
