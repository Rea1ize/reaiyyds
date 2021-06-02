package com.hsae.customview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {
    private View mContentFragmentView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("4444", "onCreateView: ");
        mContentFragmentView = inflater.inflate(R.layout.fragment_content, container, false);

        return mContentFragmentView;
    }



}
