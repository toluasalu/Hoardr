package com.zuri.pjt_95_hoardr.utils

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.zuri.pjt_95_hoardr.MainActivity
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.ItemImageBinding


/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 5:49 PM
 */
class ImageAdapter(private val fragment: Fragment, val max: Int): RecyclerAdapter<Uri>() {
    val _items = mutableSetOf<Uri>()
    val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = _items.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding as ItemImageBinding) {
            // setup the image view for uploading images
            if(holder.adapterPosition == 0){
                imageExchangeItem.setImageResource(R.drawable.ic_baseline_add_circle_24)
                root.setBackgroundResource(R.color.white)
                root.setOnClickListener {
                    if(itemCount == max){
                        Snackbar.make(fragment.requireView(),
                            "You can't upload more than $max images",Snackbar.LENGTH_SHORT).show()
                    }else{
                        val photoPickerIntent = Intent(Intent.ACTION_PICK)
                        photoPickerIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        photoPickerIntent.type = "image/*"
//                    photoPickerIntent.addCategory(Intent.CATEGORY_OPENABLE)
                        fragment.requireActivity().startActivityForResult(
                            photoPickerIntent,
                            MainActivity.IMAGE_REQUEST_CODE)
                    }
                }
            }else{
                imageExchangeItem.loadImage(
                    _items.toList()[holder.adapterPosition - 1],
                    fragment.requireActivity())
            }
            (fragment.requireActivity() as? MainActivity)?.let { activity ->
                activity.intentResult.observe(fragment){ pair ->
                    if(pair.first == MainActivity.IMAGE_REQUEST_CODE)
                        pair.second.data?.let { uri ->
                            _items.add(uri)
                            Log.e(TAG, "onBindViewHolder: Image URI $uri")
                        }
                }
            }
        }
    }
}