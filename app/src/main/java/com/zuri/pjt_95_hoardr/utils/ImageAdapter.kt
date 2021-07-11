package com.zuri.pjt_95_hoardr.utils

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zuri.pjt_95_hoardr.MainActivity
import com.zuri.pjt_95_hoardr.databinding.ItemImageBinding


/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 10-Jul-21 at 5:49 PM
 */
class ImageAdapter(private val fragment: Fragment): RecyclerAdapter<Uri>() {
    override var items: List<Uri> = mutableListOf()
    private val _items = items as MutableList<Uri>
    val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = super.getItemCount() + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding as ItemImageBinding) {
            // setup the image view for uploading images
            if(holder.adapterPosition == 0){
                imageExchangeItem.setImageResource(R.drawable.ic_baseline_add_circle_24)
                root.setBackgroundResource(R.color.white)
                root.setOnClickListener {
                    val photoPickerIntent = Intent(Intent.ACTION_PICK)
                    photoPickerIntent.type = "image/*"
                    photoPickerIntent.addCategory(Intent.CATEGORY_OPENABLE)
                    startActivityForResult(fragment.requireActivity(),
                        photoPickerIntent,
                        MainActivity.IMAGE_REQUEST_CODE, null)
                }
            }else{
                val item = items[holder.adapterPosition - 1]
                Log.e(TAG, "onBindViewHolder: $holder - $item" )
                val selectedImage = BitmapFactory.decodeStream(
                    fragment.requireActivity()
                        .contentResolver.openInputStream(item)
                )
                imageExchangeItem.setImageBitmap(selectedImage)
            }
            (fragment.requireActivity() as? MainActivity)?.let { activity ->
                activity.intentResult.observe(fragment){ pair ->
                    if(pair.first == MainActivity.IMAGE_REQUEST_CODE)
                        pair.second.data?.let { uri ->
                            _items.add(uri)
                            notifyItemInserted(items.size)
                        }
                }
            }
        }
    }
}