package com.atixiansoftwares.librarybooks;

import android.app.Application;

import com.atixiansoftwares.librarybooks.utils.ConnectivityReceiver;
import com.atixiansoftwares.librarybooks.utils.PreferenceHelper;

public class MyLibraryBooks extends Application {

    private static MyLibraryBooks mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        PreferenceHelper.getInstance().Initialize(getApplicationContext());
    }

    public static synchronized MyLibraryBooks getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
