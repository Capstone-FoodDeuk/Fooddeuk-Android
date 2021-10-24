package com.seoultech.fooddeuk.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.GitRepoNameResopnse
import com.seoultech.fooddeuk.model.GitRepoResponse
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DibsViewModel: ViewModel() {

    val storeName: MutableLiveData<List<GitRepoNameResopnse>> = MutableLiveData(listOf())

    fun getStoreName(): LiveData<List<GitRepoNameResopnse>> {
        return storeName
    }

    fun setStoreName(userName: String) {
        FooddeukAPI.getUserRepoInfo(userName).enqueue(object : Callback<List<GitRepoNameResopnse>> {
            override fun onResponse(
                call: Call<List<GitRepoNameResopnse>>,
                response: Response<List<GitRepoNameResopnse>>
            ) {
                storeName.postValue(response.body())
            }

            override fun onFailure(call: Call<List<GitRepoNameResopnse>>, t: Throwable) {
                Log.d("Fooddeuk","storeName 가져오는 통신 실패")
            }

        })
    }
}