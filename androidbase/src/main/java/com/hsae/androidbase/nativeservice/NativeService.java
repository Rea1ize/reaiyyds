package com.hsae.androidbase.nativeservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class NativeService extends Service {

    public class MyBinder extends Binder{

        String sendMessage(String msg){
            return msg + "from NativeService";
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


}
