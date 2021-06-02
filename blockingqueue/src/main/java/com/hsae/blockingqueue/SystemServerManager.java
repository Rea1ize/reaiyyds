package com.hsae.blockingqueue;

import android.util.Log;

public class SystemServerManager {

    public void startService(SystemService systemService){
        systemService.onStart();
        Log.d("mxzhq", "startService: " );
    }

}
