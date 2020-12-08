package com.example.endterm

import com.google.gson.annotations.SerializedName

data class ToDo(
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String,
)