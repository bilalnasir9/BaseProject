package com.example.baseproject.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment <VB : ViewBinding> : DialogFragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun setupUI()
    open fun isCancelableDialog() = true
    open fun getDialogWidth() = ViewGroup.LayoutParams.MATCH_PARENT
    open fun getDialogHeight() = ViewGroup.LayoutParams.WRAP_CONTENT

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(isCancelableDialog())
        dialog.setCanceledOnTouchOutside(isCancelableDialog())
        return dialog
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(getDialogWidth(), getDialogHeight())
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}