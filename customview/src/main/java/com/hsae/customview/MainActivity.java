package com.hsae.customview;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentTransitionImpl;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    final String TAG = "recyclerview";

    private FruitAdapter adapter;

    private List<Fruit> fruitList = new ArrayList<>();
    int id = 0;

    int id () {
        id++;
        return id;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy);
        Log.d(TAG, "onCreate: "+ (byte)01);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Button flush = findViewById(R.id.bt_flush);
        flush.setOnClickListener(this::onClick);

        //LayoutManager用于指定RecyclerView的布局方式，不设置的话
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//
//            @Override
//            public int getSpanSize(int position) {
//                return position % 3 == 0 ? 1 : 2;
//            }
//        });


        recyclerView.setLayoutManager(layoutManager);



        //final RecycleViewItemLine line = new RecycleViewItemLine(this, LinearLayout.HORIZONTAL,1,this.getResources().getColor(R.color.colorAccent));
        //设置添加分割线
        //recyclerView.addItemDecoration(line);

        //设置适配器
        adapter = new FruitAdapter(this);
        //添加数据并且刷新adapter
        adapter.setDatas(fruitList);
        recyclerView.setAdapter(adapter);
    }

    void initFruits(){
        for (int i = 0;i<1;i++){
            Fruit apple = new Fruit(id(),"Apple", R.drawable.ic_launcher_foreground);
            fruitList.add(apple);
            Fruit banana = new Fruit(id(),"Banana", R.drawable.ic_launcher_foreground);
            fruitList.add(banana);
            Fruit orange = new Fruit(id(),"Orange", R.drawable.ic_launcher_foreground);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(id(),"Watermelon", R.drawable.ic_launcher_foreground);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(id(),"Pear", R.drawable.ic_launcher_foreground);
            fruitList.add(pear);
            Fruit grape = new Fruit(id(),"Grape", R.drawable.ic_launcher_foreground);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(id(),"Pineapple", R.drawable.ic_launcher_foreground);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(id(),"Strawberry", R.drawable.ic_launcher_foreground);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(id(),"Cherry", R.drawable.ic_launcher_foreground);
            fruitList.add(cherry);
            Fruit mango = new Fruit(id(),"Mango", R.drawable.ic_launcher_foreground);
            fruitList.add(mango);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_flush:
                Log.d(TAG, "onClick: ");
                for (int i = 0; i<100 ;i++){
                    Fruit random = new Fruit(id(),"randomFruit"+id(),R.drawable.ic_launcher_foreground);
                    fruitList.add(random);
                }
               // adapter.notifyDataSetChanged();
                adapter.setDatas(fruitList);
                break;
        }
    }
}