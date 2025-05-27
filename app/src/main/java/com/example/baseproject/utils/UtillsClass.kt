package com.example.baseproject.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object UtillsClass {

    fun getCurrentTimestamp(): String {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return sdf.format(Date())
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }


    //  other utils method..
}
