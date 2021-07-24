package com.zuri.pjt_95_hoardr.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
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
class AddItemsUploadItemFragment : HoardrFragment() {
    private lateinit var binding: FragmentAddItemBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: StorageReference
    private lateinit var adapter: ImageAdapter
    val TAG = javaClass.simpleName


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)
        db = Firebase.firestore
        storage = Firebase.storage.reference
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val categoryDropdown = binding.textinputAddItemsCategory
            val locationDropdown = binding.textinputAddItemsLocation
            //Setup code for the Categories Dropdown
            val categories = listOf("Electronics", "Jewelry", "Clothes", "Machinery")
            val categoryAdapter = ArrayAdapter(
                requireContext(),
                R.layout.list_item, categories
            )
            (categoryDropdown.editText as? AutoCompleteTextView)?.setAdapter(categoryAdapter)

            //Setup code for the Locations Dropdown
            val locations = listOf("Lagos, Nigeria", "F.C.T, Nigeria", "Nassarawa, Nigeria")
            val locationAdapter = ArrayAdapter(requireContext(),R.layout.list_item, locations)
            (locationDropdown.editText as? AutoCompleteTextView)?.setAdapter(locationAdapter)

            setupCheckboxes()
            // setup image upload area
            val maxNumberOfImages = 4
            adapter = ImageAdapter(this@AddItemsUploadItemFragment, maxNumberOfImages)
            listAddItemsAddedImages.adapter = adapter
            listAddItemsAddedImages.layoutManager =
                GridLayoutManager(requireContext(), maxNumberOfImages + 1)
            binding.buttonAddItemContinue.setOnClickListener {
                submit()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun setupCheckboxes() = with(binding) {
        fun toggle(price: Boolean, exchange: Boolean) {
            groupAddItemsPrice.visibility = if (price) View.VISIBLE else View.GONE
            groupAddItemsExchangeFor.visibility = if (exchange) View.VISIBLE else View.GONE
        }

        val priceSelection =
            CheckBoxGroup(checkAddItemsForSale, checkAddItemsForExchange, checkAddItemsForFree)
        CheckBoxGroup(checkAddItemsAnonymous, checkAddItemsUseName)

        with(priceSelection) {
            checkAddItemsForSale.setOnCheckedListener(this) {
                toggle(
                    price = true,
                    exchange = false
                )
            }
            checkAddItemsForExchange.setOnCheckedListener(this) {
                toggle(
                    price = false,
                    exchange = true
                )
            }
            checkAddItemsForFree.setOnCheckedListener(this) {
                toggle(
                    price = false,
                    exchange = false
                )
            }
        }
    }

    private fun submit() = with(binding) {
        val path = "items/${UUID.randomUUID()}"
        val price = when {
            checkAddItemsForSale.isChecked -> textinputAddItemsPrice.editText?.text.toString()
                .toInt()
            checkAddItemsForExchange.isChecked -> -1
            else -> 0
        }
        val item = Item(
            description = textinputAddItemsDescription.editText?.text.toString(),
            title = textinputAddItemsTitle.editText?.text.toString(),
            image = path,
            owner = appViewModel.user?.id,
            location = textinputAddItemsLocation.editText?.text.toString(),
            price = price,
            uploadedAnonymously = checkAddItemsAnonymous.isChecked
        )

        fun uploadItem() {
            db.collection("items").add(item)
                .addOnSuccessListener {
                    buttonAddItemContinue.revertAnimation()
                    findNavController().navigate(
                        AddItemsUploadItemFragmentDirections.actionAddItemFragmentToSuccessFragment(
                            SuccessModel(
                                getString(R.string.success_add_item),
                                R.drawable.vector_cloud, R.id.navigation_home
                            )
                        )
                    )
                }.addOnFailureListener {
                    buttonAddItemContinue.revertAnimation()
                    Snackbar.make(
                        root,
                        "We had a problem uploading your item",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
        }

        fun uploadImage() {
            val imageRef = storage.child(path)
            buttonAddItemContinue.startAnimation()

            imageRef.putFile(adapter._items.toList().first())
                .addOnSuccessListener {
                    uploadItem()
                }.addOnFailureListener {
                    buttonAddItemContinue.revertAnimation()
                    Snackbar.make(
                        root,
                        "Image could not be uploaded\n${it.message}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
        }

        uploadImage()
    }
}