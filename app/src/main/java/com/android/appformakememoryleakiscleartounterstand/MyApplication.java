package com.android.appformakememoryleakiscleartounterstand;

import android.app.Application;

import leakcanary.LeakCanary;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
