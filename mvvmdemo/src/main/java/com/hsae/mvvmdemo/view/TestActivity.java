package com.hsae.mvvmdemo.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.hsae.mvvmdemo.viewmodel.TestLifeCycle;

import static com.hsae.mvvmdemo.view.TestActivity.Event.ON_CREATE;
import static com.hsae.mvvmdemo.view.TestActivity.Event.ON_DESTROY;

public class TestActivity extends Activity implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry;

    final String TAG = "testactivity";

    public enum Event {
        /**
         * Constant for onCreate event of the {@link LifecycleOwner}.
         */
        ON_CREATE,
        /**
         * Constant for onStart event of the {@link LifecycleOwner}.
         */
        ON_START,
        /**
         * Constant for onResume event of the {@link LifecycleOwner}.
         */
        ON_RESUME,
        /**
         * Constant for onPause event of the {@link LifecycleOwner}.
         */
        ON_PAUSE,
        /**
         * Constant for onStop event of the {@link LifecycleOwner}.
         */
        ON_STOP,
        /**
         * Constant for onDestroy event of the {@link LifecycleOwner}.
         */
        ON_DESTROY,
        /**
         * An {@link Lifecycle.Event Event} constant that can be used to match all events.
         */
        ON_ANY
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        getLifecycle().addObserver(new TestLifeCycle());

        Intent intent = new Intent("android.intent.action.lifeservicea");//Service Intent must be explicit
        intent.setPackage("com.hsae.mvvmdemo");//service所在应用的包名,android 5.0之后不允许设置隐式intent
        startService(intent);
        int resu = ON_CREATE.compareTo(ON_DESTROY);
        Log.d(TAG, "resu: "+resu);
    }




}
