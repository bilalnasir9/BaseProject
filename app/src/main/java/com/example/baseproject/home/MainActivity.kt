package com.example.baseproject.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baseproject.R
import com.example.baseproject.base.showlogD
import com.example.baseproject.databinding.ActivityMainBinding
import com.example.baseproject.retrofit.example.SomeDataFragment
import com.example.baseproject.utils.UtillsClass.getCurrentTimestamp
import com.example.qiblaapp.baseproject.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun provideViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {
//        viewModel.dummyText.observe(this) {
//            binding.maintext.text = it
//        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showlogD(message = "timestamp: " + getCurrentTimestamp())

    }

    override fun onBackPressedCallback(): Boolean {
        finish()
        return true
    }

    override fun setupViews() {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerr.id, SomeDataFragment())
            .commit()
    }
}