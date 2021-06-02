package com.hsae.androidbase.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hsae.androidbase.R;
import com.hsae.androidbase.logutils.logutils;

public class FragActivity extends Activity implements View.OnClickListener{
    private boolean flag =false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        FragmentPagerAdapter fragmentPagerAdapter;
        FragmentStatePagerAdapter fragmentStatePagerAdapter;
        FragmentStateAdapter fragmentStateAdapter;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:
                if (!flag){
                    replaceFragment(new UsedFragment());
                    flag = true;
                } else {
                    replaceFragment2(new RigthFragment());
                    flag = false;
                }
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        logutils.d("replaceFragment");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务
        transaction.replace(R.id.right_fragment, fragment);
        transaction.commit();
    }

    private void replaceFragment2(Fragment fragment) {
        logutils.d("replaceFragment2");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务
        transaction.replace(R.id.used_fragment, fragment);
        transaction.commit();
    }
}
