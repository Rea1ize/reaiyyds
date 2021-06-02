package com.hsae.javabase.reflex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.voice.AlwaysOnHotwordDetector;
import android.util.Log;

import com.hsae.javabase.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射,https://blog.csdn.net/sinat_38259539/article/details/71799078
 */
public class MainActivity extends AppCompatActivity {
    final String TAG = "4444";
    Class clazz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Log.d(TAG, "oldString:==null ");
        }

        if (savedInstanceState != null) {
            String oldString = savedInstanceState.getString("Activity");
            Log.d(TAG, "oldString: "+oldString);
        }


        //第一种方式获取Class对象
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        /**
         * 反射的本质 就是获得到class对象后，反向获取student对象的各种信息
         */

        Class stuClass = stu1.getClass();//获取Class对象
        Log.d(TAG, "stuClassName: " +stuClass.getName());


        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        Log.d(TAG, "stuClass == stuClass2: "+ (stuClass == stuClass2));//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个


        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName("com.hsae.javabase.reflex.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            Log.d(TAG, "stuClass3 == stuClass2: "+ (stuClass3 == stuClass2));//判断三种方式是否获取的是同一个Class对象
            clazz =stuClass3;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c : conArray){
          //  Log.d(TAG, "publicConstructor: " +c);
        }

        Log.d(TAG, "************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        Log.d(TAG, "onCreate: ");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor c : conArray){
            Log.d(TAG, "AllConstructor: " +c);
        }

        Log.d(TAG, "*****************获取公有、无参的构造方法*******************************");
        Constructor con = null;
        try {
            con = clazz.getConstructor(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。
        Log.d(TAG, "公有无参: "+con);


        //调用构造方法
        Object obj = null;
        try {
            obj = con.newInstance();
            Log.d(TAG, "obj = " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
           //con = clazz.getDeclaredConstructor(char.class);
           con = clazz.getDeclaredConstructor(String.class,int.class);//获得对应的构造
        } catch (Exception e) {
            e.printStackTrace();
        }
         con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        try {
            obj = con.newInstance("男",16); //使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        /**
         * 获取成员变量并调用
         */
        try {
            Fields.main();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            MethodClass.main();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Demo.setContext(getApplicationContext());
            Demo.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: oldString save");
        String string = "activity 被系统回收了怎么办？";
        outState.putString("Activity", string);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "oldString onRestoreInstanceState: ");
    }
}