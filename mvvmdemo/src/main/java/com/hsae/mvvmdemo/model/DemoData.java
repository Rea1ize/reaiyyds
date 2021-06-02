package com.hsae.mvvmdemo.model;

import androidx.lifecycle.LiveData;

public class DemoData extends LiveData<DemoData> {
    private int name;
    private int age;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
        postValue(this);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        postValue(this);
    }

}
