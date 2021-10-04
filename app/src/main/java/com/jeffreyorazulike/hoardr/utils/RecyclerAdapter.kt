package com.jeffreyorazulike.hoardr.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.io.Serializable

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 3:16 PM
 */
abstract class RecyclerAdapter<T>:
    RecyclerView.Adapter<RecyclerAdapter<T>.ViewHolder>(), Serializable {
    open var items: List<T> = listOf()

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: ViewBinding):
            RecyclerView.ViewHolder(binding.root), Serializable
}