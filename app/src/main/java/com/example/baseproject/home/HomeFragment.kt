package com.example.baseproject.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.base.showToast
import com.example.baseproject.databinding.FragmenthomeBinding
import com.example.baseproject.base.BaseFragment

class HomeFragment : BaseFragment<FragmenthomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmenthomeBinding {
        return FragmenthomeBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        binding.btnnn.setOnClickListener {
            viewModel.loadData()
        }
    }

    override fun observeViewModel() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.maintext.text = it
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let { it1 -> requireContext().showToast(it1) }
        }
    }

    override fun onFragmentBackPressed(): Boolean {
        requireContext().showToast("Back press in HomeFragment!")
        return true
    }
//    override fun onBackPressedCallback(): Boolean {
//        return true
//    }
}