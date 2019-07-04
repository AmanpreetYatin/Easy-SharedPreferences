package com.amn.easysharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.text.TextUtils
import org.jetbrains.annotations.Nullable
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class EasySharedPreference {
    var sharedPreference: SharedPreferences? = null


    companion object {
        @Volatile
        var singelton: EasySharedPreference? = null

        var defaultAppImageData: String? = null
        var lastImagePath: String? = null

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
            checkForNullKey(key)
            return getInstance().sharedPreference!!.getString(key, defValue)!!
        }

        @Nullable
        fun getInt(key: String, defValue: Int): Int {
            checkForNullKey(key)
            return getInstance().sharedPreference!!.getInt(key, defValue)!!
        }

        @Nullable
        fun getLong(key: String, defValue: Long): Long {
            checkForNullKey(key)
            return getInstance().sharedPreference!!.getLong(key, defValue)
        }

        @Nullable
        fun getFloat(key: String, defValue: Float): Float {
            checkForNullKey(key)
            return getInstance().sharedPreference!!.getFloat(key, defValue)
        }

        @Nullable
        fun getBoolean(key: String, defValue: Boolean): Boolean {
            checkForNullKey(key)
            return getInstance().sharedPreference!!.getBoolean(key, defValue)
        }

        @Nullable
        fun getStringSet(key: String, @Nullable defValue: Set<String>): Set<String>? {
            checkForNullKey(key)
            return getInstance().sharedPreference!!.getStringSet(key, defValue)

        }

        fun contains(key: String): Boolean {
            checkForNullKey(key)
            return getInstance().sharedPreference!!.contains(key)

        }

        @Nullable
        fun putString(key: String, value: String) {
            checkForNullKey(key)
            getInstance().sharedPreference!!.edit().putString(key, value).apply()
        }


        @Nullable
        fun putInt(key: String, value: Int) {
            checkForNullKey(key)
            getInstance().sharedPreference!!.edit().putInt(key, value).apply()
        }

        @Nullable
        fun putLong(key: String, value: Long) {
            checkForNullKey(key)
            getInstance().sharedPreference!!.edit().putLong(key, value).apply()
        }

        @Nullable
        fun putFloat(key: String, value: Float) {
            checkForNullKey(key)
            getInstance().sharedPreference!!.edit().putFloat(key, value).apply()
        }

        @Nullable
        fun putBoolean(key: String, value: Boolean) {
            checkForNullKey(key)
            getInstance().sharedPreference!!.edit().putBoolean(key, value).apply()
        }

        @Nullable
        fun putStringSet(key: String, @Nullable value: Set<String>) {
            checkForNullKey(key)
            getInstance().sharedPreference!!.edit().putStringSet(key, value).apply()
        }

        @Nullable
        fun getListString(key: String): List<String> {
            return ArrayList(Arrays.asList(*TextUtils.split(getInstance().sharedPreference!!.getString(key, ""), "‚‗‚")))
        }

        @Nullable
        fun putListString(key: String, stringList: List<String>) {
            checkForNullKey(key)
            val myStringList = stringList.toTypedArray()
            getInstance().sharedPreference!!.edit().putString(key, TextUtils.join(",=,", myStringList)).apply()
        }

        @Nullable
        fun putImage(theFolder: String, theImageName: String, bitmap: Bitmap): String {
            if (theFolder.isNullOrBlank() || theImageName.isNullOrBlank() ||
                    bitmap == null) {
                return ""

            }
            defaultAppImageData = theFolder
            val mFullPath = setupFullPath(theImageName)
            if (!mFullPath.equals("")) {
                lastImagePath = mFullPath
                saveBitmap(mFullPath, bitmap)
            }
            return mFullPath

        }

        fun getBitmapImage(path: String): Bitmap {
            var bitmapFromPath: Bitmap? = null
            try {
                bitmapFromPath = BitmapFactory.decodeFile(path)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return bitmapFromPath!!
        }

        fun getLastSavedImagePath(): String {
            return lastImagePath!!
        }

        fun saveBitmap(fullpath: String, bitmap: Bitmap): Boolean {
            if (fullpath.isNullOrEmpty() || bitmap == null)
                return false
            var fileCreated: Boolean = false
            var bitmapCompressed: Boolean = false
            var streamClosed: Boolean = false

            val imageFile = File(fullpath)
            if (imageFile.exists()) {
                if (!imageFile.delete())
                    return false
            }
            try {
                fileCreated = imageFile.createNewFile()

            } catch (e: IOException) {
                e.printStackTrace()
            }
            var out: FileOutputStream? = null
            try {
                out = FileOutputStream(imageFile)
                bitmapCompressed = bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)

            } catch (e: Exception) {
                e.printStackTrace()
                bitmapCompressed = false

            } finally {
                if (out != null) {
                    try {
                        out.flush()
                        out.close()
                        streamClosed = true

                    } catch (e: IOException) {
                        e.printStackTrace()
                        streamClosed = false
                    }

                }
            }

            return (fileCreated && bitmapCompressed && streamClosed)


        }

        @Nullable
        fun setupFullPath(theImageName: String): String {
            val mFolder = File(Environment.getExternalStorageDirectory(), defaultAppImageData)
            if (isExternalStorageReadable() && isExternalStorageWritable() && !mFolder.exists()) {
                if (!mFolder.mkdirs()) {
                    mFolder.mkdirs()
                }
            }
            return mFolder.path + '/' + theImageName
        }

        fun remove(key: String) {
            checkForNullKey(key)
            getInstance().sharedPreference!!.edit().remove(key).apply()
        }

        fun clear() {
            getInstance().sharedPreference!!.edit().clear().apply()

        }

        private fun checkForNullKey(key: String) {
            if (key.isBlank()) {
                throw NullPointerException("Key can not be blank or empty")
            }
        }

        fun isExternalStorageWritable(): Boolean {
            return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageDirectory())
        }

        fun isExternalStorageReadable(): Boolean {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
        }

        fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
            getInstance().sharedPreference!!.registerOnSharedPreferenceChangeListener(listener)

        }

        fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {

            getInstance().sharedPreference!!.unregisterOnSharedPreferenceChangeListener(listener)
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