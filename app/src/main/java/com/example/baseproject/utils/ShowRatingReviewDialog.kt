package com.example.baseproject.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory

object ShowRatingReviewDialog {
    var reviewManager: ReviewManager? = null
    var reviewInfo: ReviewInfo? = null
    fun initializeRatingDialog(applicationContext: Context){
        if (reviewManager==null || reviewInfo==null){
            reviewManager = ReviewManagerFactory.create(applicationContext)
            val manager = reviewManager?.requestReviewFlow()
            manager?.addOnCompleteListener { task: Task<ReviewInfo?> ->
                if (task.isSuccessful) {
                    reviewInfo = task.result
                    Log.d("ShowRatingAndroidDialog", "initializeRateDialog: success ")
                }
                else{
                    Log.d("ShowRatingAndroidDialog", "initializeRateDialog: Failed "+task.exception)
                }
            }
            manager?.addOnFailureListener {
                Log.d("ShowRatingAndroidDialog", "initializeRateDialog: Failed "+it.message)
            }
        }
    }

    fun Context.showRateDialog(runnable: Runnable){
        try {
            if (reviewInfo != null) {
                val flow = reviewManager!!.launchReviewFlow(this as Activity, reviewInfo!!)
                flow.addOnCompleteListener {
                    Log.d("ShowRatingAndroidDialog", "showRateDialog: success ")
                    Log.d("ShowRatingAndroidDialog", "showRateDialog: success "+it.isComplete)
                    Log.d("ShowRatingAndroidDialog", "showRateDialog: success "+it.isSuccessful)
                    runnable.run()
                }
                flow.addOnFailureListener {
                    Log.d("ShowRatingAndroidDialog", "showRateDialog: Failed "+it.message)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}