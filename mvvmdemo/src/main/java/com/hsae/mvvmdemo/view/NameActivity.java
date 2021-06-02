package com.hsae.mvvmdemo.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hsae.mvvmdemo.R;
import com.hsae.mvvmdemo.viewmodel.NameViewModel;
import com.hsae.mvvmdemo.viewmodel.TestLifeCycle;

public class NameActivity extends AppCompatActivity {
    private NameViewModel model;
    private TextView nameTextView;
    private Button mBtnAddData;
    final String TAG = "nameactvity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_livedata);
            nameTextView = findViewById(R.id.tx_name);
            mBtnAddData = findViewById(R.id.btn_add_data);
            getLifecycle().addObserver(new TestLifeCycle());
        // Other code to setup the activity...

        // Get the ViewModel.
        model = new ViewModelProvider(this).get(NameViewModel.class);

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                Log.d(TAG, "onChanged: newName" +newName);
                nameTextView.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        //开始注册监听
        model.getCurrentName().observe(this, nameObserver);

        mBtnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anotherName = "John Doe"+Math.random();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        model.getCurrentName().postValue(anotherName);
                        Log.d(TAG, "run: reai " +Thread.currentThread().getName());
                    }
                }).start();
            }
        });


    }
}
