package com.hsae.androidbase.executor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hsae.androidbase.logutils.logutils;

public class ExecutorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FixedThreadPool.main();
       // CachedThreadPool.main();
        //SingleThreadExecutor.main();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        logutils.d("onNewIntent");
    }
}
