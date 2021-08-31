package com.seoultech.fooddeuk.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.seoultech.fooddeuk.databinding.LayoutNoTruckDialogBinding

class NoTruckDialog : DialogFragment() {

    private lateinit var binding: LayoutNoTruckDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutNoTruckDialogBinding.inflate(inflater, container, false)
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 다이얼로그 배경 투명색으로 설정
            setDimAmount(0.7f) // 다이얼로그 프래그먼트 빈공간 진한 회색으로 설정(기본은 0.5임)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }
        binding.btnOk.setOnClickListener {
            Toast.makeText(context, "확인", Toast.LENGTH_SHORT).show()
        }
    }
}
