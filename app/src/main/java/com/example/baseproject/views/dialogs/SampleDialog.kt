package com.example.baseproject.views.dialogs

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.baseproject.R
import com.example.baseproject.base.BaseDialogFragment
import com.example.baseproject.databinding.DialogSampleBinding

class SampleDialog (
    private val onConfirm: () -> Unit,
    private val onCancel: (() -> Unit)? = null
) : BaseDialogFragment<DialogSampleBinding>() {
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogSampleBinding.inflate(inflater, container, false)

    override fun setupUI() {
        binding.btnConfirm.setOnClickListener {
            onConfirm()
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            onCancel?.invoke()
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val margin = resources.getDimensionPixelSize(R.dimen.dialog_margin)
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            window.setLayout(screenWidth - (margin * 2), ViewGroup.LayoutParams.WRAP_CONTENT)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}