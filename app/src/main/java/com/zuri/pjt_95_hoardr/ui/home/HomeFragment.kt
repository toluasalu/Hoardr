package com.zuri.pjt_95_hoardr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentHomeBinding
import com.zuri.pjt_95_hoardr.databinding.ItemHomeCategoryBinding
import com.zuri.pjt_95_hoardr.models.Item
import com.zuri.pjt_95_hoardr.utils.ItemAdapter
import com.zuri.pjt_95_hoardr.utils.RecyclerAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        layoutManager = GridLayoutManager(requireContext(), 4)
        db = Firebase.firestore
        initializeDisplay()
        return binding.root
    }

    private fun initializeDisplay() = with(binding){
        // show the navigation pieces
        requireActivity().findViewById<Group>(R.id.group_navigation).visibility = View.VISIBLE

        arguments?.let { argument ->
            HomeFragmentArgs.fromBundle(argument).user?.let {
                viewModel.user = it
                viewModel.loggedIn = true
            }
        }

        textHomeGreeting.text = if(viewModel.user != null)
            "Hello ${viewModel.user!!.first}," else "Welcome"

        if(viewModel.loggedIn) buttonHomeRegister.visibility = View.GONE
            else textHomeTimeGreeting.visibility = View.GONE
        /**
         * initialize home categories
         */
        // hide the view all option
        includeHomeCategoriesHeader.let {
            it.textCategoryViewAll.visibility = View.GONE
            it.imageCategoryViewAll.visibility = View.GONE
        }
        // load the category entries and images and display them
        listHomeCategories.adapter = CategoriesAdapter()
        listHomeCategories.layoutManager = layoutManager

        /**
         * initialize newly added items
         */
        includeHomeNewItemsHeader.let {
            it.textCategoryHeading.text = "Newly Added Items"
            // get all items from database
            db.collection("items").get().addOnSuccessListener { documents ->
                if(documents.size() == 0) it.textCategoryViewAll.visibility = View.GONE
                else {
                    val adapter = ItemAdapter(
                        viewModel.loggedIn,
                        documents.asSequence().map { document ->
                            document.toObject(Item::class.java)
                        }.toList()
                    )
                    listHomeNewItems.adapter = adapter
                    adapter.customItemCount = 10
                    listHomeNewItems.layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }
        }


        /**
         * initialize favourite items
         */
        includeHomeFavouriteItemsHeader.let {
            it.textCategoryHeading.text = "Favourite Items"
            it.textCategoryViewAll.visibility = View.GONE
        }
    }

    inner class CategoriesAdapter: RecyclerAdapter<Category>(){
        override var items = loadCategoryData(requireContext())
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemHomeCategoryBinding.inflate(layoutInflater, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = holder.binding as ItemHomeCategoryBinding
            items[position].let {
                item.textCategory.text = it.name
                item.imageCategory.setImageResource(it.image)
            }
        }
    }
}