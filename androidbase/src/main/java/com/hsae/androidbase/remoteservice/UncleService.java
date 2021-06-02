package com.hsae.androidbase.remoteservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hsae.androidbase.IMyAidlInterface;

public class UncleService extends Service {
    final String TAG = "4444";
    private IMyAidlInterface myAidlInterface;

    private ServiceConnection serviceConnection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
              int result = myAidlInterface.sum(1,2);
                Log.d(TAG, "sum: "+result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "UncleService onCreate");
        Log.d(TAG, "UncleServiceContext: "+getApplicationContext());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: action: "+intent.getAction() +" flags: "+flags+" startId: "+startId);

        if (intent.getType().equals("bind")){
            Log.d(TAG, "UncleService bind sonservice");
            Intent intent1 = new Intent();
            intent1.setPackage("com.hsae.androidbase");
            intent1.setAction("android.intent.action.SonService");
            boolean flag = bindService(intent1,serviceConnection,BIND_AUTO_CREATE);//多次bind只会触发一次onbind
            Log.d(TAG, "bindSonService: "+flag);
        }else {
            Log.d(TAG, "UncleService unbind sonservice");
            unbindService(serviceConnection);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "UncleService onDestroy");
    }
}
