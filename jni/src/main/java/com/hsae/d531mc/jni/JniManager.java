package com.hsae.d531mc.jni;

import android.util.Log;

public class JniManager {

    final String TAG = "reai";

    public static native int sum(int a , int b);

    public static native String clone(String free);

    public static native float mult(float a,float b);

    public int speak (int name){
        Log.d(TAG, "name come from jni: "+name);
        return name;
    }

    public String nativeclone (String free){
        String result = clone(free);
        return result;
    };

    public int nativeSum(int a,int b){
        int result = sum(a,b);
        return result;
    }

    public float nativemult(float a,float b){
        float result = mult(a,b);
        return result;
    };



}
