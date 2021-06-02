package com.hsae.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//import com.hsae.mvvmdemo.databinding.ActivityMainBinding;

public class LiveDataActivity extends Activity {
    private TextView mTextView;

    final String TAG = "mxz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text);

        LiveData<Location> myLocationListener = new LocationLiveData(getApplicationContext());

//        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
//        Swordsman swordsman = new Swordsman("茆训忠","S");
//        binding.setSwordsman(swordsman);
//        binding.setWife("胡钦");
//
//
//        binding.btPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.w(TAG, "onClick: 我超帅" );
//            }
//        });

    }

}