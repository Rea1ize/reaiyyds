package com.hsae.androidbase.asynctask;

import android.annotation.RequiresPermission;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.net.wifi.WifiManager.SoftApCallback;

import androidx.annotation.Nullable;

import com.hsae.androidbase.logutils.logutils;

public class TestActivity extends Activity  {


    private WifiManager manager;
    private SoftApNumCallback mApNumCallback;

    class SoftApNumCallback implements SoftApCallback {

        @Override
        public void onNumClientsChanged(int arg0) {
            // TODO Auto-generated method stub
            logutils.d("wifi连接数量" +arg0);
        }

        @Override
        public void onStateChanged(int arg0, int arg1) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApNumCallback = new SoftApNumCallback();
        manager = (WifiManager) getApplication().getSystemService(Context.WIFI_SERVICE);
        //manager.registerSoftApCallback(mApNumCallback, null);
    }


}
