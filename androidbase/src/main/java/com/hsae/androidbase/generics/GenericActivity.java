package com.hsae.androidbase.generics;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hsae.androidbase.logutils.logutils;

import java.util.ArrayList;
import java.util.List;

public class GenericActivity extends Activity {

    private ArrayList<String>[] lists = new ArrayList[10];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.实例化的时候指定泛型实参。
        Generic<String,Integer> holder = new Generic<>("1");
        holder.setValue("value");
        //holder.setValue(1);//不能编译通过


        //2.实例化的时候不指定泛型实参。
        Generic holder2 = new Generic<>();
        holder2.setValue("value");
        holder2.setValue(1);//没有执行泛型实参，可以设置不同类型

        logutils.d("holder value: "+holder.getValue());
        logutils.d("holder2 value: "+holder2.getValue());

        List<String> list = new ArrayList<>();
        //编译期异常：illegal generic type of instanceof
        //logutils.d("instanceof=" + (list instanceof List<String>));

    }




}
