package com.hsae.mvvmdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hsae.mvvmdemo.model.DemoData;

public class DemoViewModel extends ViewModel {
    private DemoData mDemoData = new DemoData();

    public DemoData getmDemoData(){
        return mDemoData;
    }
}
