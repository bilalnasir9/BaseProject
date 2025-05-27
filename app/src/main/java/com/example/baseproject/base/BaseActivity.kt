package com.example.qiblaapp.baseproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    val LOG: String = "BaseActivityLOG"

    //    private var backPressCallback: OnBackPressedCallback? = null
    private val handlerdelay = Handler(Looper.getMainLooper())


    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract fun provideViewBinding(): VB
    open fun observeViewModel() {}
    open fun setupViews() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG, "BASEONCREATE")

        _binding = provideViewBinding()
        setContentView(binding.root)
        setupViews()
        observeViewModel()
        handleBackPress()
//        backPressCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                onBackPressedCustom()
//                Log.d(LOG, "BASEONCREATE: handleOnBackPressed")
//            }
//        }
//        onBackPressedDispatcher.addCallback(this, backPressCallback!!)
    }
//    open fun onBackPressedCustom(){
//
//    }

    fun delayAndGo(delayMillis: Long, runnable: Runnable?) {
        Handler(Looper.getMainLooper()).postDelayed({
            runnable?.run()
        }, delayMillis)
        handlerdelay.postDelayed(runnable!!, delayMillis)
    }

    // Observe LiveData once
    protected fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
        observe(this@BaseActivity) {
            observer(it)
            removeObservers(this@BaseActivity)
        }
    }

    override fun onDestroy() {
        _binding = null
        handlerdelay.removeCallbacksAndMessages(null)
        super.onDestroy()
    }


    open fun onBackPressedCallback(): Boolean {
        return false // child activities can override to handle back press
    }

    private fun handleBackPress() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!onBackPressedCallback()) {
                    // Default behavior: finish the activity
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}
