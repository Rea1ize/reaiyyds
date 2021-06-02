package com.hsae.memoryleak;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {
    private Button button;
    final static String TAG = "4444";
    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        button = findViewById(R.id.bt_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendMessageDelayed(Message.obtain(),10000);
                //myHandler.sendMessageDelayed(Message.obtain(),10000);
                finish();
            }
        });
    }

    final  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: what: "+msg.what +"arg1: "+msg.arg1);
        }
    };

    private static class MyHandler extends Handler{
        private final WeakReference<HandlerActivity> mActivity;

        public MyHandler(HandlerActivity handlerActivity){
            mActivity = new WeakReference<HandlerActivity>(handlerActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: what: "+msg.what +"arg1: "+msg.arg1);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG, "====HandlerActivity has been recycled!");
    }
}
