package com.hsae.customview;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


public class MyDiffItemCallback extends DiffUtil.ItemCallback<Fruit> {
    
    final String TAG = "recyclerview";
    
    @Override
    public boolean areItemsTheSame(@NonNull Fruit oldItem, @NonNull Fruit newItem) {
        Log.d(TAG, "areItemsTheSame: ");
        boolean isSame = (TextUtils.equals(oldItem.getName(),newItem.getName()));
        return isSame;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Fruit oldItem, @NonNull Fruit newItem) {
        Log.d(TAG, "areContentsTheSame: ");
        return false;
    }
}
