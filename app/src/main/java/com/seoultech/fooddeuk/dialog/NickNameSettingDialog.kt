package com.seoultech.fooddeuk.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.seoultech.fooddeuk.databinding.LayoutNickNameSettingDialogBinding

class NickNameSettingDialog : DialogFragment() {

    private lateinit var binding: LayoutNickNameSettingDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LayoutNickNameSettingDialogBinding.inflate(inflater, container, false)
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDimAmount(0.7f)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }
        binding.btnOk.setOnClickListener {
            dialog?.cancel()
        }
    }
}