package com.zuri.pjt_95_hoardr.models

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 5:08 AM
 */
data class Item(
    val description: String? = "",
    val image: String? = null,
    val isAnonymous: String? = "",
    val listType: String? = "",
    val location: String? = "",
    val price: String? = 0,
    val title: String? = "",
    val user: User? = null,
    val key: String? = null
)