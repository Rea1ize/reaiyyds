package com.hsae.customview;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MyDiffCallback extends DiffUtil.Callback {

    private List<Fruit> oldList;
    private List<Fruit> newList;

    private MyDiffCallback(List<Fruit> oldList , List<Fruit> newList){
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    //旧数据长度
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    //新数据长度
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    //判断是否是同一个item
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Fruit oldItem = oldList.get(oldItemPosition);
        Fruit newItem = newList.get(newItemPosition);
        return false;
    }

    @Override
    //如果是同一个item,此方法用于判断是否同一个item的内容也想通
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Fruit oldItem = oldList.get(oldItemPosition);
        Fruit newItem = newList.get(newItemPosition);
        if (!TextUtils.equals(oldItem.getName(),newItem.getName())){
            return false;
        }
        return false;
    }


}
