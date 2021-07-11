package com.zuri.pjt_95_hoardr.models

import java.io.Serializable

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 1:03 AM
 */
data class User(
    val first: String? = "",
    val last:String? = "",
    val email: String? = "",
    val password: String? = "",
    val phoneNumber: String? = "",
    val country: String? = "",
    val state: String? = "",
    val lga: String? = ""
): Serializable