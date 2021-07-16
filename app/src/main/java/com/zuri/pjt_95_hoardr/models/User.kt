package com.zuri.pjt_95_hoardr.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 1:03 AM
 *
 * Represents a user entity in the Hoardr app
 */
@IgnoreExtraProperties
data class User(
    @DocumentId val id: String = "",
    val first: String? = "",
    val last: String? = "",
    val email: String? = "",
    val password: String? = "",
    val phoneNumber: String? = "",
    val country: String? = "",
    val state: String? = "",
    val lga: String? = "",
    val favourites: MutableList<String> = mutableListOf()
): Serializable