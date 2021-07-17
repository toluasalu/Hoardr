package com.zuri.pjt_95_hoardr.models.fragment_initializers

import com.zuri.pjt_95_hoardr.utils.RecyclerAdapter
import java.io.Serializable

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 11-Jul-21 at 11:16 PM
 */
data class SearchableModel(
    val searchIsVisible: Boolean = false,
    val adapter: RecyclerAdapter<out Any>?,
    val numberOfColumns: Int = 1
): Serializable