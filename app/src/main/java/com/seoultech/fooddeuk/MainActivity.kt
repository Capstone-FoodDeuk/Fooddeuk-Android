package com.seoultech.fooddeuk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.databinding.ActivityMainBinding
import com.seoultech.fooddeuk.mypage.MyPageActivity
import com.seoultech.fooddeuk.storeSetting.StoreSettingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        subscribeName()
        tmpButtonsClickListener() // TODO : 후에 점점 기능들 붙여지면 지울 것
    }

    private fun subscribeName() {
        mainViewModel.getRepoList().observe(this, Observer {
            binding.tmpTextView.text = it.toString()
        })
    }

    private fun tmpButtonsClickListener() {
        binding.tmpSettingStore.setOnClickListener {
            val intent = Intent(this, StoreSettingActivity::class.java)
            startActivity(intent)
        }
        binding.tmpMyPage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
        binding.tmpChohee.setOnClickListener {
            mainViewModel.setRepoList("choheeis")
        }
        binding.tmpMinha.setOnClickListener {
            mainViewModel.setRepoList("minha721")
        }
        binding.tmpJunyoung.setOnClickListener {
            mainViewModel.setRepoList("anzun0")
        }
    }
}