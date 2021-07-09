package com.zuri.pjt_95_hoardr.api.models


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("item_image")
    val itemImage: String
)