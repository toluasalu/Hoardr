package com.zuri.pjt_95_hoardr.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.zuri.pjt_95_hoardr.databinding.ItemProductBinding
import com.zuri.pjt_95_hoardr.models.Item
import java.io.File


/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 5:07 AM
 */
class ItemAdapter(
    private val loggedIn: Boolean,
    override var items: List<Item>,
    private val fragment: Fragment
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

        fun loadImage(imageUri: String){
            val file = File(imageUri.substringAfter("item/"))
            val localFile = if(file.exists()) file else File.createTempFile(file.toString(), null)

            fun showImage(){
                imageItemProduct.loadImage(
                    localFile.path.toUri(), fragment.requireActivity())
            }

            if(!localFile.exists()) {
                Firebase.storage.reference.child(imageUri).getFile(localFile)
                    .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot?> {
                        showImage()
                    }).addOnFailureListener(OnFailureListener {
                        Log.e(TAG, "loadImage: Image couldn't load")
                    })
            }else showImage()
        }

        items[holder.adapterPosition].let {
            textItemProductAmount.text = it.price
            textItemProductName.text = it.title
            textItemProductOwner.text = if(it.owner.isNullOrEmpty()) "Anonymous" else it.owner
            it.image?.let { image -> loadImage(image) }
        }

        root.setOnClickListener {

        }
    }
}