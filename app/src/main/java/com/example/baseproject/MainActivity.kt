package com.example.baseproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baseproject.databinding.ActivityMainBinding
import com.example.baseproject.room.noteappexample.NotesFragment
import com.example.qiblaapp.baseproject.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: MainViewModel by viewModels()

    override fun provideViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {
//        viewModel.dummyText.observe(this) {
//            binding.maintext.text = it
//        }



//        viewModel.isLoading.observe(this) { isLoading ->
//            // binding.progressBar.isVisible = isLoading
//        }
//
//        viewModel.errorMessage.observe(this) {
//            showToast(it)
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
    }

    override fun onBackPressedCallback(): Boolean {
        finish()
        return true
    }

    override fun setupViews() {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerr.id, NotesFragment())
            .commit()

    //        binding.btnnn.setOnClickListener {
//                viewModel.loadData()
//        }
    }
}