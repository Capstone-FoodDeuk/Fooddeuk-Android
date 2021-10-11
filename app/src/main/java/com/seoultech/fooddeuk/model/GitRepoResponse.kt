package com.seoultech.fooddeuk.model

import com.google.gson.annotations.SerializedName

data class GitRepoResponse(
    @SerializedName("name")
    var repoName: String
)
