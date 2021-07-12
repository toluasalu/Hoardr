package com.zuri.pjt_95_hoardr.ui.add_items

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentAddItemBinding
import com.zuri.pjt_95_hoardr.models.Item
import com.zuri.pjt_95_hoardr.models.fragment_initializers.SuccessModel
import com.zuri.pjt_95_hoardr.utils.*
import java.util.*

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 2:55 PM
 */
class AddItemsUploadItemFragment: HoardrFragment() {
    private lateinit var binding: FragmentAddItemBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: StorageReference
    private lateinit var adapter: ImageAdapter
    val TAG = javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentAddItemBinding.inflate(inflater, container,false)
        db = Firebase.firestore
        storage = Firebase.storage.reference
        initializeContent()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun initializeContent() = with(binding){
        super.initializeContent()
        setupCheckboxes()
        // setup image upload area
        val maxNumberOfImages = 4
        adapter = ImageAdapter(this@AddItemsUploadItemFragment, maxNumberOfImages)
        listAddItemsAddedImages.adapter = adapter
        listAddItemsAddedImages.layoutManager = GridLayoutManager(requireContext(), maxNumberOfImages+1)
        binding.buttonAddItemContinue.setOnClickListener {
            submit()
        }
    }

    private fun setupCheckboxes() = with(binding){
        fun toggle(price: Boolean, exchange: Boolean){
            groupAddItemsPrice.visibility = if(price) View.VISIBLE else View.GONE
            groupAddItemsExchangeFor.visibility = if(exchange) View.VISIBLE else View.GONE
        }

        val priceSelection = CheckBoxGroup(checkAddItemsForSale, checkAddItemsForExchange, checkAddItemsForFree)
        val userSelection = CheckBoxGroup(checkAddItemsAnonymous, checkAddItemsUseName)

        with(priceSelection){
            checkAddItemsForSale.setOnCheckedListener(this) { toggle(price = true, exchange = false) }
            checkAddItemsForExchange.setOnCheckedListener(this) { toggle(price = false, exchange = true) }
            checkAddItemsForFree.setOnCheckedListener (this) { toggle(price = false, exchange = false) }
        }
    }

    private fun submit() = with(binding){
        val path = "items/${UUID.randomUUID()}"
        val item = Item(
            description = textinputAddItemsDescription.editText?.text.toString(),
            title = textinputAddItemsTitle.editText?.text.toString(),
            image = path,
            owner = if(checkAddItemsAnonymous.isChecked) {
                "Anonymous"
            }else appViewModel.user?.let {
                "${it.first} ${it.last}"
            },
            listType = CheckBoxGroup(checkAddItemsForSale,
                checkAddItemsForExchange,
                checkAddItemsForFree)
                .getCheckedOr(checkAddItemsForFree).text.toString(),
            location = textinputAddItemsLocation.editText?.text.toString(),
            price = textinputAddItemsPrice.editText?.text.toString()
        )

        fun uploadItem(){
            db.collection("items").add(item)
                .addOnSuccessListener {
                    findNavController().navigate(
                        AddItemsUploadItemFragmentDirections.actionAddItemFragmentToSuccessFragment(
                            SuccessModel(getString(R.string.success_add_item),
                                R.drawable.vector_cloud, R.id.navigation_home)
                        )
                    )
                }.addOnFailureListener {
                    Snackbar.make(root, "We had a problem uploading your item", Snackbar.LENGTH_SHORT).show()
                }
        }
        
        fun uploadImage(){
            val imageRef = storage.child(path)
            val progress = ProgressDialog(requireContext())

            progress.setTitle("Uploading Image")
            progress.show()
            imageRef.putFile(adapter._items.toList().first()).addOnProgressListener { _ ->
                OnProgressListener<UploadTask.TaskSnapshot> {
                    progress.progress = (100 * it.bytesTransferred / it.totalByteCount).toInt()
                    val message = "Uploaded: ${progress.progress}%"
                    progress.setMessage(message)
                    Log.d(TAG, "uploadImage: $message")
                }
            }.addOnSuccessListener {
                progress.dismiss()
                uploadItem()
            }.addOnFailureListener{
                progress.dismiss()
                Snackbar.make(root,"Image could not be uploaded\n${it.message}",Snackbar.LENGTH_SHORT).show()
            }
        }

        uploadImage()
    }
}