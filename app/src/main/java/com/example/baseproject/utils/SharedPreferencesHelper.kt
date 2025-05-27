package com.example.baseproject.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {
    private const val PREF_NAME = "base_app_prefs"
    private lateinit var prefs: SharedPreferences

    // Initialize once in Application class
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private val preferences: SharedPreferences
        get() = if (::prefs.isInitialized) {
            prefs
        } else {
            throw IllegalStateException("SharedPreferencesHelper not initialized. Call init(context) in Application class.")
        }

    // Generic Put Methods
    fun putString(key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }
    fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun putFloat(key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }
    fun putStringSet(key: String, value: Set<String>) {
        preferences.edit().putStringSet(key, value).apply()
    }

    // Generic Get Methods
    fun getString(key: String, default: String? = null): String? =
        preferences.getString(key, default)

    fun getInt(key: String, default: Int = 0): Int =
        preferences.getInt(key, default)

    fun getBoolean(key: String, default: Boolean = false): Boolean =
        preferences.getBoolean(key, default)

    fun getLong(key: String, default: Long = 0L): Long =
        preferences.getLong(key, default)

    fun getFloat(key: String, default: Float = 0f): Float =
        preferences.getFloat(key, default)

    fun getStringSet(key: String, default: Set<String>? = null): Set<String>? =
        preferences.getStringSet(key, default)

    // Utility
    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun contains(key: String): Boolean = preferences.contains(key)

    fun setLanguage(language: String) {
        putString("app_language", language)
    }

    fun getLanguage(): String =
        getString("app_language", "en") ?: "en" // Default English

}