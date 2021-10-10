package com.seoultech.fooddeuk

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.GitRepoResponse
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private var repoList: MutableLiveData<List<GitRepoResponse>> = MutableLiveData(listOf(GitRepoResponse(repoName = "")))

    fun getRepoList(): LiveData<List<GitRepoResponse>> {
        return repoList
    }

    fun setRepoList(userName: String) {
        // Retrofit call 호출
        FooddeukAPI.getUserRepoList(userName).enqueue(object : Callback<List<GitRepoResponse>> {
            override fun onResponse(
                call: Call<List<GitRepoResponse>>,
                response: Response<List<GitRepoResponse>>
            ) {
                repoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<GitRepoResponse>>, t: Throwable) {
                // 서버 통신 실패
                Log.d("kimchohee", "서버 통신 실패 $t")
            }
        })
    }
}