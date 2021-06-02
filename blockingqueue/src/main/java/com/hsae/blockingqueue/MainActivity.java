package com.hsae.blockingqueue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

     IAutoService iAutoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**非阻塞队列实现生产者消费者
         * NoBlock noBlock = new NoBlock();
         * noBlock.run();
         */

       Block block = new Block();
        try {
            block.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemServerManager systemServerManager = new SystemServerManager();
        systemServerManager.startService(new AudioService());
    }


    private  IAutoService getService() {

        iAutoService = new IAutoService() {
            @Override
            public IBinder asBinder() {
                return null;
            }
        };

        return  iAutoService;


    }


}