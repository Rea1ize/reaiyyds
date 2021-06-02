package com.hsae.d531mc.jni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hsae.ccs2plus.AesUtils;

public class MainActivity extends AppCompatActivity {

    final String TAG = "ReAi";
    private int result;
    private String cloneResult;
    private float multresult;
    private String path;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("nativereai");

        System.loadLibrary("nativedemo");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "stringFromJNI: "+stringFromJNI());

        JniManager jniManager = new JniManager();
        result = jniManager.nativeSum(4,4);
        cloneResult = jniManager.nativeclone("我热爱的");
        multresult = jniManager.nativemult(2,3);
        Log.d(TAG, "nativemult: "+multresult);
        Log.d(TAG, "nativeSum: "+result);
        Log.d(TAG, "nativeclone: " + cloneResult);
        Log.d(TAG, "path: "+path);
    }

    @Override
    protected void onResume() {
        super.onResume();
        path = getFilesDir().getPath();
        AesUtils aesUtils = new AesUtils();
        aesUtils.nativeEncryptFile(path+"/md5",path+"/md6");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}