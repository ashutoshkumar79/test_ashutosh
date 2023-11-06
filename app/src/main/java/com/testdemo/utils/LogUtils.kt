package com.testdemo.utils

import android.util.Log
import androidx.viewbinding.BuildConfig

object LogUtils {

    private var DEBUG = BuildConfig.DEBUG

    fun d(tag: String, message: String) {
        if(DEBUG) {
            Log.d(tag, message)
        }
    }

    fun e(tag: String, message: String) {
        if(DEBUG) {
            Log.e(tag, message)
        }
    }

    fun i(tag: String, message: String) {
        if(DEBUG) {
            Log.i(tag, message)
        }
    }

    fun v(tag: String, message: String) {
        if(DEBUG) {
            Log.v(tag, message)
        }
    }

    fun w(tag: String, message: String) {
        if(DEBUG) {
            Log.w(tag, message)
        }
    }


}