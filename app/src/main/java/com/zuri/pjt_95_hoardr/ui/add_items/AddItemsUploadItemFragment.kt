package com.zuri.pjt_95_hoardr.ui.add_items

import android.os.Bundle
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
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentAddItemBinding
import com.zuri.pjt_95_hoardr.models.Item
import com.zuri.pjt_95_hoardr.models.fragment_initializers.SuccessModel
import com.zuri.pjt_95_hoardr.utils.CheckBoxGroup
import com.zuri.pjt_95_hoardr.utils.ImageAdapter
import com.zuri.pjt_95_hoardr.utils.setOnCheckedListener

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 2:55 PM
 */
class AddItemsUploadItemFragment: Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentAddItemBinding.inflate(inflater, container,false)
        initializeDisplay()
        db = Firebase.firestore
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.listAddItemsAddedImages.adapter?.notifyDataSetChanged()
    }

    private fun initializeDisplay() = with(binding){
        setupCheckboxes()
        // setup image upload area
        val maxNumberOfImages = 4
        listAddItemsAddedImages.adapter = ImageAdapter(this@AddItemsUploadItemFragment, maxNumberOfImages)
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

    private fun submit(){
        val item = Item()
        db.collection("items").add(item).addOnSuccessListener {
            findNavController().navigate(
                AddItemsUploadItemFragmentDirections.actionAddItemFragmentToSuccessFragment(
                    SuccessModel(getString(R.string.success_add_item),
                        R.drawable.vector_cloud, R.id.navigation_home)
                )
            )
        }.addOnFailureListener {
            Snackbar.make(requireView(), "We had a problem uploading your item", Snackbar.LENGTH_SHORT).show()
        }
    }
}