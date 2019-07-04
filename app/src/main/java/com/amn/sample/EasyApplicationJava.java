package com.amn.sample;

import android.app.Application;
import android.content.Context;

import com.amn.easysharedpreferences.EasySharedPreferenceConfig;

public class EasyApplicationJava extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EasySharedPreferenceConfig.Companion.initDefault(new EasySharedPreferenceConfig.Builder()
                .inputFileName("easy_preference")
                .inputMode(Context.MODE_PRIVATE)
                .build());
    }
}