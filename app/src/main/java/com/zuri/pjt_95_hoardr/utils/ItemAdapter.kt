package com.zuri.pjt_95_hoardr.utils

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zuri.pjt_95_hoardr.databinding.ItemProductBinding
import com.zuri.pjt_95_hoardr.models.Item
import com.zuri.pjt_95_hoardr.models.User


/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 5:07 AM
 */
class ItemAdapter(
    private val viewModel: HoardrViewModel,
    private val onclick: ((Item, User?) -> Unit)?
): RecyclerAdapter<Item>() {
    var customItemCount = 0
    init {
        viewModel.items?.let {
            items = it
        }
    }

    override fun getItemCount() =
        if (customItemCount == 0 || customItemCount > items.size)
        items.size
        else customItemCount

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.binding as ItemProductBinding){
        items[holder.adapterPosition].let {
            var user: User? = null
            Firebase.firestore.collection("users")
                .document(it.owner!!).get().addOnSuccessListener { document ->
                    document?.let { ds ->
                        user = ds.toObject(User::class.java)
                        textItemProductOwner.text = if(it.uploadedAnonymously == true) "Anonymous"
                        else "${user?.first} ${user?.last}"
                    }
                }
            textItemProductAmount.text = when(it.price){
                0 -> "Free"
                -1 -> "Exchange"
                else -> it.price.toString()
            }
            textItemProductName.text = it.title
            it.image?.let { image ->
                imageItemProduct.loadImage(Uri.parse(image), true)
            }
            imageItemProductFavourite.setOnClickListener { _ ->
                user?.favourites?.add(it.id)
                user?.id?.let { it1 ->
                    Firebase.firestore.collection("users").document(it1)
                        .update("favourites", FieldValue.arrayUnion(it.id))
                }
            }
            root.setOnClickListener { _ ->
                onclick?.let { onclick -> onclick(it, user) }
            }
        }
    }
}