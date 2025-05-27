package com.example.baseproject.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleManager {

    //call in Application Class
    fun setLocale(context: Context): Context {
        val language = SharedPreferencesHelper.getLanguage()
        return updateResources(context, language)
    }

    //Call in Language screen to change language
    fun changeLanguage(context: Context, newLang: String): Context {
        SharedPreferencesHelper.setLanguage(newLang)
        return updateResources(context, newLang)
    }
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}