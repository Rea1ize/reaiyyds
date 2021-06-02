package com.hsae.androidbase.handlerthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hsae.androidbase.logutils.logutils;

/**
 * 线程通讯
 */
public class ItcActivity extends Activity {

    private static volatile boolean flag = true;

    private static class Sender {

        private  static class Message{
            private static String msg;

            static void run(){
               logutils.d("threadname :" +Thread.currentThread().getName()+" msg"+ msg);
            }
        }

       static final ThreadLocal<Sender> sThreadLocal = new ThreadLocal<Sender>();

        static final ThreadLocal<Message> smsg = new ThreadLocal<Message>();

       static {
           sThreadLocal.set(new Sender());
           smsg.set(new Message());
       }

        void sendMessage(String msg){
            logutils.d("Thread :"+Thread.currentThread().getName()+" msg:"+msg);
            Message.run();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (flag){
                    //Sender sender = new Sender();
                   // sender.sendMessage("mxz");
                    Sender.smsg.get().msg = "mxz";
                    Sender.sThreadLocal.get().sendMessage(Sender.smsg.get().msg);
                }

            }
        }).start();



    }


}
