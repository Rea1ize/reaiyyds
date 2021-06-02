package com.hsae.javabase.reflex;

import android.util.Log;

import java.lang.reflect.Method;

public class MethodClass {

     static final String TAG = "4444_MethodClass";

     public static void main() throws Exception{

         //1.获取Class对象
         Class stuClass = Class.forName("com.hsae.javabase.reflex.Student");
         //2.获取所有公有方法
         Log.d(TAG, "***************获取所有的”公有“方法*******************");
         stuClass.getMethods();
         Method[] methodArray = stuClass.getMethods();
         for(Method m : methodArray){
             Log.d(TAG, "Method: "+m);
         }

         Log.d(TAG, "***************获取所有的方法，包括私有的*******************");
         methodArray = stuClass.getDeclaredMethods();
         for(Method m : methodArray){
         }
         Log.d(TAG, "***************获取公有的show1()方法*******************");
         Method m = stuClass.getMethod("show1", String.class);


         //实例化一个Student对象
         Object obj = stuClass.getConstructor().newInstance();
         m.invoke(obj, "刘德华");




         Log.d(TAG, "***************获取私有的show4()方法******************");
         m = stuClass.getDeclaredMethod("show4", int.class);
         Log.d(TAG, "Method: "+m);

         m.setAccessible(true);//解除私有限定
         Object result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
         Log.d(TAG, "返回值：" + result);
     }
}
