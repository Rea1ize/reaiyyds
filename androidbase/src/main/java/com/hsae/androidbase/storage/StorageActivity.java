package com.hsae.androidbase.storage;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hsae.androidbase.logutils.logutils;

import java.io.File;

/**
 * 存储分为
 *
 * 内部存储
 * 外部存储
 * 外部缓存目录
 * 外部永久目录
 * SD卡存储
 */

public class StorageActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = getApplication().getFilesDir();
        logutils.d("FilesDir： path: "+file.getPath());// /data/user/0/com.hsae.androidbase/files

        File cacheFile = getApplication().getCacheDir();
        logutils.d("getCacheDir: "+cacheFile.getPath());// /data/user/0/com.hsae.androidbase/cache

        File code = getApplication().getCodeCacheDir();
        logutils.d("getCodeCacheDir: "+code.getPath());//   /data/user/0/com.hsae.androidbase/code_cache

        String[] files = getApplication().fileList();
        for (int i =0; i<files.length;i++){
            logutils.d("files "+files[i]);
        }

    }

}
