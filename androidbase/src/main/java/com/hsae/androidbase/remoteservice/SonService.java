package com.hsae.androidbase.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hsae.androidbase.IMyAidlInterface;

/**
 * android:isolatedProcess ：设置 true 意味着，服务会在一个特殊的进程下运行，这个进程与系统其他进程分开且没有自己的权限。
 * 与其通信的唯一途径是通过服务的API(bind and start)。
 */
public class SonService extends Service {

    static final String TAG = "4444";

    private class SonServiceBinder extends IMyAidlInterface.Stub{

        @Override
        public int sum(int a, int b) throws RemoteException {
            return a+b;
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "SonService onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "SonService onStart");
    }
    

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "SonService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "SonService onBind");
        ServiceManager.addService("com.hsae.auto.IBINDER_MYBINDER",new SonServiceBinder());
        return new SonServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "SonService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SonService onDestroy");
    }
}
