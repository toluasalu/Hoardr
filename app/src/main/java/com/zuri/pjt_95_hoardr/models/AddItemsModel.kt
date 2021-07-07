package com.zuri.pjt_95_hoardr.models

import android.content.Context
import com.zuri.pjt_95_hoardr.R

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 3:26 PM
 */

fun loadItemsData(context: Context) = mutableListOf<AddItem>().apply {
    context.resources.let {
        val names = it.getStringArray(R.array.add_item_entry)
        val images = it.obtainTypedArray(R.array.add_item_entry_image)
        val descriptions = it.getStringArray(R.array.add_item_entry_description)
        for((index, name) in names.withIndex()){
            add(AddItem(name, images.getResourceId(index, 0), descriptions[index]))
        }
        images.recycle()
    }
}.toList()

data class AddItem(val name: String, val image: Int, val description: String)