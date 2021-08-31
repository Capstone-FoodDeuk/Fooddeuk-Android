package com.seoultech.fooddeuk.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityInputReviewBinding

class InputReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityInputReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var category : String? = null

        binding.spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent != null) {
                    category = parent.getItemAtPosition(position).toString()
                }
            }
        }

        binding.btnInputOk.setOnClickListener {
            val intent = Intent(this, CheckReviewActivity::class.java)
            intent.putExtra("storeName", binding.etStoreName.text.toString())
            intent.putExtra("category", category)
            intent.putExtra("starScore", binding.etStar.text.toString())
            intent.putExtra("tasteGood", binding.etTasteGood.text.toString())
            intent.putExtra("tasteSoSo", binding.etTasteSoso.text.toString())
            intent.putExtra("tasteBad", binding.etTasteBad.text.toString())
            intent.putExtra("amountGood", binding.etAmountGood.text.toString())
            intent.putExtra("amountSoSo", binding.etAmountSoso.text.toString())
            intent.putExtra("amountBad", binding.etAmountBad.text.toString())
            intent.putExtra("kindGood", binding.etKindGood.text.toString())
            intent.putExtra("kindSoSo", binding.etKindSoso.text.toString())
            intent.putExtra("kindBad", binding.etKindBad.text.toString())
            startActivity(intent)
        }
    }
}