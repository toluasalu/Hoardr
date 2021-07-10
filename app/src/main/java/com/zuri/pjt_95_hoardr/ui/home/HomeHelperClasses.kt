package com.zuri.pjt_95_hoardr.ui.home

import android.content.Context
import com.zuri.pjt_95_hoardr.R

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 3:26 PM
 */
fun loadCategoryData(context: Context) = mutableListOf<Category>().apply {
    context.resources.let {
        val names = it.getStringArray(R.array.home_category_entry)
        val images = it.obtainTypedArray(R.array.home_category_entry_image)
        for((index, name) in names.withIndex()){
            add(Category(name, images.getResourceId(index, 0)))
        }
        images.recycle()
    }
}.toList()

data class Category(val name: String, val image: Int)