package com.hsae.javabase.reflex;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Fields {
    final static String TAG = "4444_Fields";
    
    public static void main() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        
        //1.获取Class对象
        Class stuClass = Class.forName("com.hsae.javabase.reflex.Student");
        //2.获取字段
        Log.d(TAG, "************获取所有公有的字段********************");
        Field[] fieldArray = stuClass.getFields();
        for(Field f : fieldArray){
            Log.d(TAG, "public fileld : "+f);
        }

        Log.d(TAG, "************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = stuClass.getDeclaredFields();
        for(Field f : fieldArray){
            Log.d(TAG, "all fileld: "+f);
        }
        
        
        Log.d(TAG, "*************获取公有字段**并调用***********************************");
        Field f = stuClass.getField("name");
        Log.d(TAG, "name Field : "+f);
        //获取一个对象
        Object obj = stuClass.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
        //为字段设置值
        f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        Student stu = (Student)obj;
        Log.d(TAG, "验证姓名：" + stu.name);


        Log.d(TAG, "**************获取私有字段****并调用********************************");
        f = stuClass.getDeclaredField("phoneNum");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obj, "18888889999");
        Log.d(TAG, "验证电话：" + stu.toString());

    }
}
