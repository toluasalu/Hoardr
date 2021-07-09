package com.zuri.pjt_95_hoardr.api.models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("first_name")
    val firstName: String? = "",
    @SerializedName("last_name")
    val lastName: String? = "",
    @SerializedName("local_gov")
    val localGov: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("password2")
    val password2: String? = "",
    @SerializedName("phone_number")
    val phoneNumber: String? = "",
    @SerializedName("state")
    val state: String? = ""
)