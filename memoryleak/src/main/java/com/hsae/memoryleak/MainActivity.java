package com.hsae.memoryleak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 在Java中，非静态的内部类和匿名类会隐式地持有一个他们外部类的引用。静态内部类则不会。
 */
public class MainActivity extends AppCompatActivity {
    private static Object inner; //因为这个static的inner持有，activity内部类的的引用，内部类又持有activity的引用，所以activty回收不掉。static的生命周期长
    private Button button;
    final String TAG = "4444";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        button = findViewById(R.id.bt_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInnerClass();
                finish();
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG, "====MainActivity has been recycled!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    void createInnerClass() {
        class InnerClass {
        }
        inner = new InnerClass();//inner指向innerclass对象，也可以说成引用，
    }


}