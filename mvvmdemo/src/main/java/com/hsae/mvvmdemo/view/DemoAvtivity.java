package com.hsae.mvvmdemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.hsae.mvvmdemo.R;
import com.hsae.mvvmdemo.model.DemoData;
import com.hsae.mvvmdemo.viewmodel.DemoViewModel;

public class DemoAvtivity extends AppCompatActivity {
    private static final String TAG = "demo";
    private Button mBtnAddData;
    private DemoViewModel mDemoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);
        mBtnAddData = findViewById(R.id.btn_add_data);
        mDemoViewModel = ViewModelProviders.of(this).get(DemoViewModel.class);//获取ViewModel,让ViewModel与此activity绑定
        mDemoViewModel.getmDemoData().observe(this, new Observer<DemoData>() {//注册观察者,观察数据的变化
            @Override
            public void onChanged(DemoData demoData) {
                Log.d(TAG, "onChanged: 数据 "+demoData.getName());
            }
        });

        mBtnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 已经点击");
                mDemoViewModel.getmDemoData().setName((int)Math.random()*100);
            }
        });


    }
}
