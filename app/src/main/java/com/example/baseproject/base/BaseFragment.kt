package com.example.baseproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!



    open fun onFragmentBackPressed(): Boolean = false // Allow fragment to intercept back press






    abstract fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    open fun observeViewModel() {}
    open fun setupViews() {}
//    open fun onBackPressedCallback(): Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = provideViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleBackPress()
        setupViews()
        observeViewModel()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    protected fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
        observe(viewLifecycleOwner) {
            observer(it)
            removeObservers(viewLifecycleOwner)
        }
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!onFragmentBackPressed()) {
                        isEnabled = false
//                        requireActivity().onBackPressed()
                        requireActivity().onBackPressedDispatcher.onBackPressed()

                    }
                }
            }
        )
    }

}