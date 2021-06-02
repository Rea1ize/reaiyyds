package com.hsae.blockingqueue;

import android.util.Log;

public class AudioService extends SystemService{

    private SystemServerManager manager = new SystemServerManager();
    @Override
    void onStart() {
        Log.w("mxzhq", "onStart: ");
       // manager.startService(new AudioService());
    }

}
