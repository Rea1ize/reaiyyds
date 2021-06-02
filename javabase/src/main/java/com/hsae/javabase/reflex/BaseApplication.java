package com.hsae.javabase.reflex;

import android.app.Application;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseApplication extends Application {

    final static  String TAG = "4444_app";


    @Override
    public void onCreate() {
        super.onCreate();
      //  getCurrentProcessNameByActivityThread();
        try {
            Log.d(TAG, "onCreate: "+getCurrentProcessName());
        } catch (Exception e) {

        }
    }

    //CSDN
    public static String getCurrentProcessNameByActivityThread() {
        String processName = null;
        try {
            final Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader())
                    .getDeclaredMethod("currentProcessName", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            final Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                processName = (String) invoke;
            }
        } catch (Throwable e) {
        }
        return processName;
    }



    //我的反射
    public static String getCurrentProcessName() throws Exception {
       long a =  System.currentTimeMillis();;
       Class ActivityThread = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader());
       Method m = ActivityThread.getDeclaredMethod("currentProcessName");
       m.setAccessible(true);
       Object result = m.invoke(null);
       long b =  System.currentTimeMillis();;
       String processname =  (String)result;
       Log.d(TAG, "usetime"+(b-a));

       return processname;

    }

}
