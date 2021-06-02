package com.hsae.myservice;

import android.app.Application;
import android.app.Service;
import android.os.ServiceManager;
import android.util.Log;


public class MyApplication extends Application {

    private MyService.IRecoveryProxy mRecovery;
    @Override
    public void onCreate() {
        super.onCreate();
        mRecovery = new MyService.IRecoveryProxy();
        //ServiceManager.addService("reai", mRecovery);//只有系统应用才可以执行这操作
        Log.d("mxz", "onCreate: ");
    }
}
