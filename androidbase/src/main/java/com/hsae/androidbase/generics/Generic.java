package com.hsae.androidbase.generics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Generic<T,A>  {

    private T value;

    private A numb;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public A getnumb() {
        return numb;
    }

    public Generic(T value) {
        this.value = value;
    }


    public Generic() {
    }

    public <T> void setMethodValue1(T t) {
        System.out.println("t=" + t);
    }


    //静态方法无法访问类定义上的泛型，因此，如果 静态方法的参数类型是可变的，那么要将 静态方法定义成泛型方法。
//    public static void showValue2(Generic<T,A> list){
//
//    }


    @Override
    public boolean equals(@Nullable Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
