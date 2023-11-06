package com.testdemo.data.preference

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtils private constructor(private val context: Context) {

    val name: String = "my_preference"
    var preferences: SharedPreferences? = null

    init {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    companion object : SPSingletonHolder<SharedPreferenceUtils, Context>(::SharedPreferenceUtils)


    fun setString(key: String, value: String) {
        if (preferences != null)
            preferences?.edit()?.putString(key, value)?.apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return if (preferences != null) {
            preferences?.getString(key, defaultValue)
        } else defaultValue
    }

    fun setBoolean(key: String, value: Boolean) {
        if (preferences != null)
            preferences?.edit()?.putBoolean(key, value)?.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean? {
        return if (preferences != null) {
            preferences?.getBoolean(key, defaultValue)
        } else defaultValue
    }

    fun setInt(key: String, value: Int) {
        if (preferences != null)
            preferences?.edit()?.putInt(key, value)?.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int? {
        return if (preferences != null) {
            preferences?.getInt(key, defaultValue)
        } else defaultValue
    }

    fun setLong(key: String, value: Long) {
        if (preferences != null)
            preferences?.edit()?.putLong(key, value)?.apply()
    }

    fun getLong(key: String, defaultValue: Long): Long? {
        return if (preferences != null) {
            preferences?.getLong(key, defaultValue)
        } else defaultValue
    }

    fun setFloat(key: String, value: Float) {
        if (preferences != null)
            preferences?.edit()?.putFloat(key, value)?.apply()
    }

    fun getFloat(key: String, defaultValue: Float): Float? {
        return if (preferences != null) {
            preferences?.getFloat(key, defaultValue)
        } else defaultValue
    }

    fun removeKey(key: String) {
        if (preferences != null)
            preferences?.edit()?.remove(key)?.apply()
    }

    fun removeAll() {
        if (preferences != null)
            preferences?.edit()?.clear()?.apply()
    }

}