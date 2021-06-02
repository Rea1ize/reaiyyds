package com.hsae.memoryleak;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AsyncTaskActivity extends AppCompatActivity {
    private Button button;
    final static String TAG = "4444";
    private AsyncTask mAsy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.bt_next);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 startAsyncTask();
               // startStaticAsyncTask();
                finish();
            }

        });
        

    }

    private static class MyAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (true){
                Log.d(TAG, "doInBackground: ");
            }
        }
    }

    void startAsyncTask() {

      mAsy =   new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                while (true){
                    Log.d(TAG, "doInBackground: ");
                }
                //return null;
            }
        }.execute();
    }



    void startStaticAsyncTask() {

        new MyAsyncTask().execute();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mAsy !=null){
//            mAsy = null;
//            Log.d(TAG, "mAsyfinalize: ");
//        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG, "finalize: ");
    }
}
