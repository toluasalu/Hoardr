package com.zuri.pjt_95_hoardr.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 5:08 AM
 */
@IgnoreExtraProperties
data class Item(
    @DocumentId val id: String = "",
    val description: String? = "",
    val image: String? = null,
    val owner: String? = "",
    val location: String? = "",
    val price: Int = 0,
    val title: String? = "",
    val uploadedAnonymously: Boolean? = false
): Serializable