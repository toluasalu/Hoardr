package com.zuri.pjt_95_hoardr.models.fragment_initializers

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.ItemAddItemBinding
import com.zuri.pjt_95_hoardr.utils.RecyclerAdapter

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 3:26 PM
 */

fun loadItemsData(context: Context) = mutableListOf<AddItem>().apply {
    context.resources.let {
        val names = it.getStringArray(R.array.add_item_entry)
        val images = it.obtainTypedArray(R.array.add_item_entry_image)
        val descriptions = it.getStringArray(R.array.add_item_entry_description)
        val navigations = it.obtainTypedArray(R.array.add_item_entry_navigation)
        for((index, name) in names.withIndex()){
            add(AddItem(name, images.getResourceId(index, 0),
                descriptions[index], navigations.getResourceId(index, 0)))
        }
        images.recycle()
        navigations.recycle()
    }
}.toList()

data class AddItem(
    val name: String,
   val image: Int,
   val description: String,
   val navigation: Int)

class AddItemsAdapter(private val fragment: Fragment): RecyclerAdapter<AddItem>(){
    override var items = loadItemsData(fragment.requireContext())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAddItemBinding.inflate(fragment.layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.binding as ItemAddItemBinding
        items[position].let {
            item.textAddItemTitle.text = it.name
            item.imageAddItemIcon.setImageResource(it.image)
            item.textAddItemDescription.text = it.description
            item.root.setOnClickListener { _ ->
                findNavController(fragment).navigate(it.navigation)
            }
        }
    }
}