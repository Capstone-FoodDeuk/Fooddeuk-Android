package com.seoultech.fooddeuk.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.ceoOnOff.CeoOnOffViewModel
import com.seoultech.fooddeuk.databinding.FragmentOnOffConfirmDialogBinding
import android.view.*


class OnOffConfirmDialog : DialogFragment() {

    private lateinit var binding: FragmentOnOffConfirmDialogBinding
    private lateinit var viewModel: CeoOnOffViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnOffConfirmDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CeoOnOffViewModel::class.java)

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }
        binding.btnOk.setOnClickListener {
            dialog?.cancel()
            viewModel.setTruckOff(true)
        }
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = requireContext().resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels

        // set custom dialog
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 다이얼로그 배경 투명색으로 설정
            setLayout((dpWidth * 0.9).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT) // 다이얼로그 width 디바이스 가로 길이의 90% 차지
            setDimAmount(0.7f) // 다이얼로그 프래그먼트 빈공간 진한 회색으로 설정(기본은 0.5임)
        }
    }

    companion object {
        fun newInstance() = OnOffConfirmDialog()
    }
}