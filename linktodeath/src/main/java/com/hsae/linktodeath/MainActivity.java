package com.hsae.linktodeath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

import com.hsae.autosdk.recovery.IRecovery;
import com.hsae.autosdk.recovery.IRecoveryCallback;

import java.net.ConnectException;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "mxzhq";
    private IRecovery mIRecovery;
    private RemoteCallbackList<IRecovery> remoteCallbackList = new RemoteCallbackList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.hsae.myservice","com.hsae.myservice.MyService");
        intent.setComponent(componentName);
        //boolean result = bindService(intent,connection, Context.BIND_AUTO_CREATE);
        //Log.d(TAG, "initService:"+result);
        Log.d(TAG, "initService:"+Thread.currentThread().getName());
        startService(intent);
    }


        private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
            @Override
            public void binderDied() {
                Log.d(TAG, "binderDied...");
                Log.d(TAG, "binderDied: "+Thread.currentThread().getName());
                if(mIRecovery == null){
                    Log.d(TAG, "binderDied: mIRecovery == null");
                    return;
                }
                mIRecovery.asBinder().unlinkToDeath(mDeathRecipient,0);//将设置的死亡代理标志清除
                mIRecovery = null;
            }
        };


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceDisconnected: ");
            mIRecovery = IRecovery.Stub.asInterface(service);
            Log.d(TAG, "aaaaa: "+remoteCallbackList.getRegisteredCallbackCount());
            ;
//            ServiceManager.addService(IBINDER_RECOVERY,service);
            try {
                mIRecovery.registerRecoveryCallback(iRecoveryCallback);
                Log.d(TAG, "onServiceConnected: registerRecoveryCallback "+iRecoveryCallback.hashCode());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                service.linkToDeath(mDeathRecipient,0);
                Log.d(TAG, "onServiceConnected: "+mIRecovery);
                Log.d(TAG, "onServiceConnected: "+Thread.currentThread().getName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    private IRecoveryCallback iRecoveryCallback = new IRecoveryCallback.Stub() {
        @Override
        public void reConnectStubResponse() throws RemoteException {
            Log.d(TAG, "reConnectStubResponse...");
        }
    };

}