package com.hsae.androidbase.asynctask;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hsae.androidbase.R;
import com.hsae.androidbase.logutils.logutils;

public class AsyncTaskActivity extends Activity implements DownLoadAsyncTask.UpdateUI{
    private static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE=0x11;
    private static String DOWNLOAD_FILE_JPG_URL="https://t7.baidu.com/it/u=2621658848,3952322712&fm=193&f=GIF";
    private ProgressBar progressBar;
    private Button downloadBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        progressBar=  findViewById(R.id.progressbar);
        downloadBtn= (Button) findViewById(R.id.downloadBtn);
        //create DownLoadAsyncTask
        final DownLoadAsyncTask  downLoadAsyncTask= new DownLoadAsyncTask(AsyncTaskActivity.this);
        //set Interface
        downLoadAsyncTask.setUpdateUIInterface(this);
        //start download
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //execute
                downLoadAsyncTask.execute(DOWNLOAD_FILE_JPG_URL);
            }
        });

        //android 6.0 权限申请
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //android 6.0 API 必须申请WRITE_EXTERNAL_STORAGE权限
            logutils.d("申请权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                logutils.d("Permission Granted");
            } else {
                // Permission Denied
                logutils.d("Permission Denied");
            }
        }
    }

    @Override
    public void UpdateProgressBar(Integer values) {
        logutils.d("process: "+values);
        progressBar.setProgress(values);;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
