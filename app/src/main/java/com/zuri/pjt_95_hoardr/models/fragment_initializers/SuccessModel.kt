package com.zuri.pjt_95_hoardr.models.fragment_initializers

import java.io.Serializable

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 6:41 AM
 */
data class SuccessModel(
    val content: String,
    val image: Int,
    val navigation: Int): Serializable
