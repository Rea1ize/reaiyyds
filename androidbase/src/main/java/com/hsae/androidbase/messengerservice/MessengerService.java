package com.hsae.androidbase.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.hsae.androidbase.logutils.logutils;

public class MessengerService extends Service {

    public static final int MSG_FROMCLIENT = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        logutils.d("MessengerService onCreate");
    }

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_FROMCLIENT:
                    logutils.d("收到客户端信息："+msg.getData().get("msg"));
            }
        }

    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }
}
