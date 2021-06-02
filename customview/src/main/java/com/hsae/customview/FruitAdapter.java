package com.hsae.customview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private AsyncListDiffer<Fruit> asyncListDiffer;
    final String TAG = "recyclerview";

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitname = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter (Context context){
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        asyncListDiffer = new AsyncListDiffer<Fruit>(this,new MyDiffItemCallback());
    }



    public void setDatas(List<Fruit> newList){
        asyncListDiffer.submitList(newList);
    }

    public Fruit getItem(int position){
     return asyncListDiffer.getCurrentList().get(position);
    }


   @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       Log.d(TAG, "onCreateViewHolder: ");
        View view = layoutInflater.inflate(R.layout.fruit_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = getItem(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitname.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        //内部维护了一个list数组，getCurrentList()不会报空指针
        //submitList()方法会对空list做处理，返回一个没有内容的list
        return asyncListDiffer.getCurrentList().size();
    }


}
