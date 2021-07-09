package com.zuri.pjt_95_hoardr.api.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: List<Image>? = null,
    @SerializedName("is_anonymous")
    val isAnonymous: String? = "",
    @SerializedName("is_favourite")
    val isFavourite: Boolean? = null,
    @SerializedName("list_type")
    val listType: String? = "",
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("title")
    val title: String? = ""
)