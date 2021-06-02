package com.hsae.linktodeath;

import android.app.Application;
import android.os.RemoteException;
import android.util.Log;

public class LinktodeathApplication extends Application {
    private final String TAG = "mxzhq";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "LinktodeathApplication onCreate...");
        try {
            Recovery.getInstance().initRecovery(getApplicationContext());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
