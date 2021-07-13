package com.zuri.pjt_95_hoardr.utils

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zuri.pjt_95_hoardr.databinding.ItemProductBinding
import com.zuri.pjt_95_hoardr.models.Item


/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 5:07 AM
 */
class ItemAdapter(
    private val loggedIn: Boolean,
    override var items: List<Item>
): RecyclerAdapter<Item>() {
    var customItemCount = 0
    val TAG = javaClass.simpleName

    override fun getItemCount() =
        if (customItemCount == 0 || customItemCount > items.size)
        items.size
        else customItemCount

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.binding as ItemProductBinding){
        if(!loggedIn)
            imageItemProductFavourite.visibility = View.GONE

        items[holder.adapterPosition].let {
            textItemProductAmount.text = it.price
            textItemProductName.text = it.title
            textItemProductOwner.text = if(it.owner.isNullOrEmpty()) "Anonymous" else it.owner
            it.image?.let { image ->
                imageItemProduct.loadImage(Uri.parse(image), true)
            }
        }

        root.setOnClickListener {

        }
    }
}