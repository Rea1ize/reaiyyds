package com.hsae.javabase.reflex;

import android.util.Log;

public class Student {
   static final String TAG = "4444_student";

    /**
     * 字段
     * @param str
     */

    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                + ", phoneNum=" + phoneNum + "]";
    }



    //---------------构造方法-------------------
    //（默认的构造方法）
    Student(String str){
        Log.d(TAG, "(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public Student(){
        Log.d(TAG, "调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Student(char name){
        Log.d(TAG, "姓名：" + name);
    }

    //有多个参数的构造方法
    public Student(String name ,int age){
        //这的执行效率有问题，以后解决。
        Log.d(TAG, "姓名："+name+"年龄："+ age);
    }

    //受保护的构造方法
    protected Student(boolean n){
        Log.d(TAG, "受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Student(int age){
        Log.d(TAG, "私有的构造方法   年龄："+ age);
    }

    //**************成员方法***************//

    public void show(){
        Log.d(TAG, "show: ");
    }

    public  void show1(String s){
        Log.d(TAG, "调用了：公有的，String参数的show1(): s = " + s );
    }
    protected void show2(){
        System.out.println("调用了：受保护的，无参的show2()");
    }
    void show3(){
        System.out.println("调用了：默认的，无参的show3()");
    }
    private String show4(int age){
        System.out.println("调用了，私有的，并且有返回值的，int参数的show4(): age = " + age);
        return "abcd";
    }



}
