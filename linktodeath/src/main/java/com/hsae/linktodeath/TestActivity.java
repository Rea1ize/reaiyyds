package com.hsae.linktodeath;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hsae.autosdk.diagnose.Diagnose;
import com.hsae.autosdk.os.Soc;


public class TestActivity extends Activity {

    private Soc mSoc;

    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            switch (msg.what){

                case 100:
                    int a = msg.arg1;
                    Log.d("mxzhq", "handleMessage arg1: "+a);

            }

            return false;
        }
    }){

    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Soc.SocListener mSocListener = new Soc.SocListener() {

            @Override
            public void onProgressChanged(int i) {

            }

            @Override
            public void mcanPhoneMenuSelection(byte b) {

            }

            @Override
            public void onUsbDeviceChanged(int i) {

            }

            @Override
            public void onMainAudioChanged(int i) {

            }

            @Override
            public void onShutDownNotify() {

            }

            @Override
            public void onUpMeterHalVersionNumber(String s) {

            }

            @Override
            public void onUpMeterSoftVersionNumber(String s) {

            }
        };

        mSoc = new Soc();
        mSoc.registerListener(mSocListener);

        Diagnose md = new Diagnose();
        try {
            md.checkAirConn();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        mhandler.obtainMessage(100,100).sendToTarget();


    }





}
