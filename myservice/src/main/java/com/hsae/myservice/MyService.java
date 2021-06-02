package com.hsae.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hsae.autosdk.recovery.IRecoveryCallback;



public class MyService extends Service {

    private static String TAG = "mxzhqmyservice";

  //  private RemoteCallbackList<IRecoveryCallback> callbackList= new RemoteCallbackList<>() ;

    @Override
    public void onCreate() {
        Log.d(TAG, "MyService onCreate...");
        Log.d(TAG, "onCreate: "+Thread.currentThread().getName());
        super.onCreate();
    //    Log.d(TAG, "callbackList: size"+callbackList.getRegisteredCallbackCount());
    }

//    public void notifyApp(){
//        int n = callbackList.beginBroadcast();
//        for (int i=0;i < n ;i++){
//           IRecoveryCallback l =  callbackList.getBroadcastItem(i);
//           // Log.d(TAG, "notifyApp: IRecoveryCallback"+l);
//           if (l !=null){
//               try {
//                   l.reConnectStubResponse();
//               } catch (RemoteException e) {
//                   e.printStackTrace();
//               }
//           }
//
//        }
//    }

    public static class IRecoveryProxy extends com.hsae.autosdk.recovery.IRecovery.Stub{

        @Override
        public void registerRecoveryCallback(IRecoveryCallback dl) throws RemoteException {

        }

        @Override
        public void unregisterRecoveryCallback(IRecoveryCallback dl) throws RemoteException {

        }

        @Override
        public void sum() throws RemoteException {
            int a = 1/0;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: "+Thread.currentThread().getName());
        return new IRecoveryProxy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MyService");
    }
}
