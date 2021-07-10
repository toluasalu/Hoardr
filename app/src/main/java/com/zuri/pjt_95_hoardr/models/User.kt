package com.zuri.pjt_95_hoardr.models

import java.io.Serializable

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 1:03 AM
 */
data class User(
    val first: String? = null,
    val last:String? = null,
    val email: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null
): Serializable