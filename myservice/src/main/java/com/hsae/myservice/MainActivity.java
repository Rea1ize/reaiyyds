package com.hsae.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;
import android.os.UserHandle;

import com.hsae.autosdk.recovery.IRecovery;
import com.hsae.autosdk.recovery.IRecoveryCallback;

public class MainActivity extends AppCompatActivity {

    final String TAG = "4444";

    private IRecovery mIRecovery;

    private Context mCOntext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_simple_spinner_dropdown_item);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mIRecovery = IRecovery.Stub.asInterface(service);
//            try {
//                mIRecovery.registerRecoveryCallback(mIRecoveryCallback);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//            try {
//                mIRecovery.sum();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IRecoveryCallback mIRecoveryCallback = new IRecoveryCallback.Stub() {
        @Override
        public void reConnectStubResponse() throws RemoteException {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.hsae.androidbase.remoteservice","com.hsae.androidbase.remoteservice.UncleService");
        intent.setComponent(componentName);
        intent.setClassName("com.hsae.androidbase.remoteservice","com.hsae.androidbase.remoteservice.UncleService");
        intent.setAction("android.intent.action.UncleService");
        boolean result = bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

        Log.d(TAG, "bindService: "+result);
        //bindservice是一个异步操作，所以要在onServiceConnect之后操作binder,否则可能空指针
//        mIRecovery.registerRecoveryCallback(mIRecoveryCallback);
//        mIRecovery.sum();



    }



}