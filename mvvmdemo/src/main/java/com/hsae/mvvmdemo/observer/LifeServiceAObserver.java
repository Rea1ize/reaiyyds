package com.hsae.mvvmdemo.observer;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeServiceAObserver implements LifecycleObserver {
    final String TAG = "lifeserviceA";
    
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        Log.d(TAG, "onCreate: ");
    }
    
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStartCommand(){
        Log.d(TAG, "onStartCommand: ");
    }
    
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestory(){
        Log.d(TAG, "onDestory: ");
    }
    
}
