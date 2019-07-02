package com.amn.sample

import android.app.Application
import android.content.Context
import com.amn.easysharedpreferences.EasySharedPreferenceConfig

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        EasySharedPreferenceConfig.initDefault(EasySharedPreferenceConfig.Builder()
                .setFileName("easy_preference")
                .setMode(Context.MODE_PRIVATE)
                .build())
    }
}