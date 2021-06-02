package com.hsae.mvvmdemo.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

public class TransActivity extends AppCompatActivity {
    final String TAG = "reai";
    MutableLiveData<String> mutableLiveData1;
    MutableLiveData<String> mutableLiveData2;
    MutableLiveData<Boolean> liveDataswitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mutableLiveData1 = new MutableLiveData<>();
        mutableLiveData2 = new MutableLiveData<>();
        liveDataswitch = new MutableLiveData<Boolean>();

        LiveData translivedata = Transformations.switchMap(liveDataswitch,new Function<Boolean,LiveData<String>>(){

            @Override
            public LiveData<String> apply(Boolean input) {
                if (input){
                    return mutableLiveData1;
                }else {
                    return mutableLiveData2;
                }
            }


        });

        translivedata.observe(this,new Observer<String>(){

            @Override
            public void onChanged(String s) {
                Log.d(TAG, "onChanged: "+s);
            }

        });

        liveDataswitch.postValue(false);

        mutableLiveData2.postValue("android进阶之光");
        mutableLiveData2.postValue("android进阶之光1");
        mutableLiveData1.postValue("android进阶解密");


        int intsysVersion = 0;

        intsysVersion = Integer.parseInt(Build.VERSION.SDK);

        Log.d(TAG, "sdkversion: "+intsysVersion);


    }
}
