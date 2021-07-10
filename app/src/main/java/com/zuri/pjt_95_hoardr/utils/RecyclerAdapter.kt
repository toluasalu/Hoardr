package com.zuri.pjt_95_hoardr.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 3:16 PM
 */
abstract class RecyclerAdapter<T>: RecyclerView.Adapter<RecyclerAdapter<T>.ViewHolder>() {
    open var items: List<T> = listOf()

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: ViewBinding):
            RecyclerView.ViewHolder(binding.root)
}