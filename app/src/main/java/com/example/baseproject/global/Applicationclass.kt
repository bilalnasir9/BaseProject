package com.example.baseproject.global

import android.app.Application
import com.example.baseproject.utils.ShowRatingReviewDialog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Applicationclass:Application() {
    override fun onCreate() {
        super.onCreate()

        ShowRatingReviewDialog.initializeRatingDialog(this)
    }
}