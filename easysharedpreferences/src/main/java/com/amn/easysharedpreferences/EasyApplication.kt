package com.amn.easysharedpreferences

import android.app.Application
import android.content.Context


class EasyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        EasySharedPreferenceConfig.initDefault(EasySharedPreferenceConfig.Builder()
                .setFileName("easy_preference")
                .setMode(Context.MODE_PRIVATE)
                .build())
    }
}