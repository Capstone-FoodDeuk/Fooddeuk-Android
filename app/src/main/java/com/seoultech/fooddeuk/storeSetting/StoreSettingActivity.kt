package com.seoultech.fooddeuk.storeSetting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.ceoOnOff.CeoOnOffActivity
import com.seoultech.fooddeuk.databinding.ActivityStoreSettingBinding
import com.seoultech.fooddeuk.model.httpBody.Menu
import com.seoultech.fooddeuk.model.httpBody.OwnerRequest

class StoreSettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoreSettingBinding
    private lateinit var ownerViewModel: OwnerViewModel
    private lateinit var category: String
    private val data = arrayListOf<MenuData>()
    var methods = arrayListOf<String>()
    var menus = arrayListOf<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recycler view
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MenuAdapter(data)

        // view model
        ownerViewModel = ViewModelProvider(this).get(OwnerViewModel::class.java)
        subscribeViewModel()

        // back click
        binding.ivBack.setOnClickListener {
            finish()
        }

        // category click
        categoryClick()

        // register number check
        binding.btnCertification.setOnClickListener {
            Toast.makeText(this, "인증 완료", Toast.LENGTH_SHORT).show()
        }

        // menu add click
        binding.btnAdd.setOnClickListener {
            addMenus()
            addRecyclerView()
        }

        // payment method click
        paymentMethodClick()

        // ok click
        binding.btnSettingOk.setOnClickListener {
            val ownerInfo = getOwnerInfoFromView()
            callOwnerAPI(ownerInfo)

            val intent = Intent(this, CeoOnOffActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun subscribeViewModel() {
        ownerViewModel.ownerOkCode.observe(this, {
            if (it) {
                Toast.makeText(this, "가게 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "가게 설정이 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addMenus() {
        val menuTransfer = Menu(binding.etMenu.text.toString(), binding.etCost.text.toString().toLong())
        menus.add(menuTransfer)
    }

    private fun addRecyclerView() {
        val menu = MenuData(binding.etMenu.text.toString(), binding.etCost.text.toString(), R.drawable.ic_cancel)
        data.add(menu)

        binding.recyclerView.adapter?.notifyDataSetChanged()

        binding.etMenu.text = null
        binding.etCost.text = null
    }

    private fun getOwnerInfoFromView(): OwnerRequest {
        return OwnerRequest(
            name = binding.etStoreName.text.toString(),
            category = category,
            registerNum = binding.etRegisterNum.text.toString(),
            menus = menus,
            description = binding.etDescription.text.toString(),
            methods = methods
        )
    }

    private fun callOwnerAPI(ownerInfo: OwnerRequest) = ownerViewModel.requestOwner(ownerInfo)

    // 결제수단 클릭
    private fun paymentMethodClick() {
        binding.chipCard.setOnClickListener {
            methods.add("CARD")
        }

        binding.chipCash.setOnClickListener {
            methods.add("CASH")
        }

        binding.chipBankTransfer.setOnClickListener {
            methods.add("BANK_TRANSFER")
        }
    }

    // 카테고리 클릭
    private fun categoryClick() {
        binding.btnCategoryTako.setOnClickListener {
            setTakoClick()
            category = "Takoyaki"
        }
        binding.btnCategoryGunbam.setOnClickListener {
            setGunbamClick()
            category = "Chestnuts"
        }
        binding.btnCategoryGoguma.setOnClickListener {
            setGogumaClick()
            category = "GOGUMA"
        }
        binding.btnCategoryApple.setOnClickListener {
            setAppleClick()
            category = "Fruit"
        }
        binding.btnCategoryBungeo.setOnClickListener {
            setBungeoClick()
            category = "FishBread"
        }
        binding.btnCategorySundae.setOnClickListener {
            setSundaeClick()
            category = "Snack"
        }
    }

    private fun setTakoClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setGunbamClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setGogumaClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setAppleClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setBungeoClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setSundaeClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
    }
}