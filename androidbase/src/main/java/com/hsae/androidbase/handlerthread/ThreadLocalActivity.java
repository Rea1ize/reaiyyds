package com.hsae.androidbase.handlerthread;

import android.app.Activity;
import android.graphics.RenderNode;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hsae.androidbase.logutils.logutils;

public class ThreadLocalActivity extends Activity {
    //ThreadLocal 线程内部的存储类
    final ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Nullable
        @Override
        protected String initialValue() {
            return "我是重写初始化的返回值";
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                logutils.d("value: "+value);

                //数据存储以后，只有再指定线程中可以获取到存储的数据，对于其他线程来说则无法获取到数据。
                threadLocal.set("我是thread1设置后的值");
                String value2 = threadLocal.get();

                logutils.d("value2: "+value2);

                //避免占用大量无意义的内存占用
                threadLocal.remove();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String value1 = threadLocal.get();
                logutils.d("thread2 getvalue: "+value1);

                threadLocal.set("我是thread2设置后的值");
                String value2 = threadLocal.get();
                logutils.d("thread2 getvalue2: "+value2);
            }
        }).start();



    }

}
