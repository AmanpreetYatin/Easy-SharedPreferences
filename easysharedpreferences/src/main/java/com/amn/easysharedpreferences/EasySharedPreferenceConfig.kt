package com.amn.easysharedpreferences

import android.content.Context

open class EasySharedPreferenceConfig {
    var fileName: String? = null
    var mode: Int? = null

    companion object {
        var instance: EasySharedPreferenceConfig? = null

        fun initDefault(config: EasySharedPreferenceConfig) {
            instance = config
        }
    }

    fun getInstance(): EasySharedPreferenceConfig {
        if (instance == null) {
            instance = EasySharedPreferenceConfig(Builder())
        }
        return instance as EasySharedPreferenceConfig
    }


    fun getFileNamee(): String {
        return fileName!!
    }

    fun getMode(): Int {
        return mode!!
    }

    constructor(builder: Builder) {
        fileName = builder.fileName
        mode = builder.mode
    }


    class Builder {
        var fileName: String = BuildConfig.APPLICATION_ID + ".App_Preference"
        var mode: Int = Context.MODE_PRIVATE

        fun setFileName(fileKey: String): Builder {
            this.fileName = fileKey
            return this
        }

        fun setMode(mode: Int): Builder {
            this.mode = mode
            return this
        }

        fun build(): EasySharedPreferenceConfig {
            return EasySharedPreferenceConfig(this)
        }
    }

}