package com.amn.easysharedpreferences

import android.content.Context
import android.content.SharedPreferences
import org.jetbrains.annotations.Nullable

class EasySharedPreference {
    var sharedPreference: SharedPreferences? = null

    companion object {
        @Volatile
        var singelton: EasySharedPreference? = null

        fun getInstance(): EasySharedPreference {
            if (singelton == null) {
                synchronized(EasySharedPreference::class.java) {
                    if (singelton == null) {
                        if (EasySharedPreferenceProvider.contextq == null) {
                            throw IllegalStateException("context can not be nul")
                        }
                        singelton = EasySharedPreference(EasySharedPreferenceProvider.contextq!!)
                    }
                }
            }
            return singelton!!
        }

        @Nullable
        fun getString(key: String, defValue: String): String {
            return getInstance().sharedPreference!!.getString(key, defValue)!!
        }

        @Nullable
        fun getInt(key: String, defValue: Int): Int {
            return getInstance().sharedPreference!!.getInt(key, defValue)!!
        }

        @Nullable
        fun getLong(key: String, defValue: Long): Long {
            return getInstance().sharedPreference!!.getLong(key, defValue)
        }

        @Nullable
        fun getFloat(key: String, defValue: Float): Float {
            return getInstance().sharedPreference!!.getFloat(key, defValue)
        }

        @Nullable
        fun getBoolean(key: String, defValue: Boolean): Boolean {
            return getInstance().sharedPreference!!.getBoolean(key, defValue)
        }

        fun contains(key: String): Boolean {
            return getInstance().sharedPreference!!.contains(key)

        }

        @Nullable
        fun putString(key: String, value: String) {
            getInstance().sharedPreference!!.edit().putString(key, value).apply()
        }

        @Nullable
        fun putInt(key: String, value: Int) {
            getInstance().sharedPreference!!.edit().putInt(key, value).apply()
        }

        @Nullable
        fun putLong(key: String, value: Long) {
            getInstance().sharedPreference!!.edit().putLong(key, value).apply()
        }

        @Nullable
        fun putFloat(key: String, value: Float) {
            getInstance().sharedPreference!!.edit().putFloat(key, value).apply()
        }

        @Nullable
        fun putBoolean(key: String, value: Boolean) {
            getInstance().sharedPreference!!.edit().putBoolean(key, value).apply()
        }

        fun remove(key: String) {
            getInstance().sharedPreference!!.edit().remove(key).apply()
        }

        fun clear() {
            getInstance().sharedPreference!!.edit().clear().apply()

        }
    }

    constructor(context: Context) {
        sharedPreference = context.getSharedPreferences(EasySharedPreferenceConfig.instance!!.getFileNamee(),
                EasySharedPreferenceConfig.instance!!.getMode())
    }


    fun getAll(): Map<String, *> {
        return getInstance().sharedPreference!!.all
    }


}