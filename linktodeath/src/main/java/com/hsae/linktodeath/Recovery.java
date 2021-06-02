package com.hsae.linktodeath;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.hsae.autosdk.recovery.IRecovery;

public class Recovery {
    public static final String IBINDER_RECOVERY = "com.hsae.auto.IBINDER_RECOVERY";
    private final String TAG = Recovery.class.getSimpleName();
    private Context mContext;
    private IRecovery mIRecovery;

    private volatile boolean result;

    private Intent intent;

    private Recovery(){}

    public static Recovery getInstance(){
        return RecoveryHolder.mRecovery;
    }

    private static class RecoveryHolder {
        private static final Recovery mRecovery = new Recovery();
    }

    public void initRecovery(Context context) throws RemoteException {
        mContext = context;
       // ServiceManager.getService(IBINDER_RECOVERY).linkToDeath(mDeathRecipient,0);
        bindAutoService();
    }

    public void bindAutoService (){
        intent = new Intent();
        ComponentName componentName = new ComponentName("com.hsae.myservice","com.hsae.myservice.MyService");
        intent.setComponent(componentName);
        boolean result = mContext.bindService(intent,connection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "bindAutoService: "+result);
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

            while (!result){
                long time = System.currentTimeMillis();
                //使用while循环模拟sleep
                Log.d(TAG, "0.5s");
                while ((System.currentTimeMillis() - time) < 500){

                }
                result = mContext.bindService(intent,connection, Context.BIND_AUTO_CREATE);
                Log.d(TAG, "binderDied: result"+result);
            }
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIRecovery = IRecovery.Stub.asInterface(service);
            try {
                //ServiceManager.getService(IBINDER_RECOVERY).linkToDeath(mDeathRecipient,0);
                Log.d(TAG, "onServiceConnected:sss "+service);
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
}
