package com.example.baseproject.retrofit.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.base.BaseFragment
import com.example.baseproject.base.showToast
import com.example.baseproject.base.showlogD
import com.example.baseproject.databinding.FragmentsomedataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SomeDataFragment : BaseFragment<FragmentsomedataBinding>() {
    private val viewModel: SomeDataViewModel by viewModels()

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentsomedataBinding {
        return FragmentsomedataBinding.inflate(inflater, container, false)
    }
    override fun setupViews() {
        binding.btnLoaddata.setOnClickListener {
            viewModel.getSomeData()
        }
    }

    override fun observeViewModel() {
        viewModel.someData.observe(viewLifecycleOwner) {
            it.forEach {
                item->
                showlogD(message = item.title)
            }
            requireContext().showToast("Data fetched success view logcat")
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let { it1 ->
                showlogD(message = it1)
                requireContext().showToast(it1)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }


    override fun onFragmentBackPressed(): Boolean {
        return true
    }
}