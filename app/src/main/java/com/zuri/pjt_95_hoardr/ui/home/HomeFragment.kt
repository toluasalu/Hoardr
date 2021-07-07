package com.zuri.pjt_95_hoardr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentHomeBinding
import com.zuri.pjt_95_hoardr.databinding.ItemHomeCategoryBinding
import com.zuri.pjt_95_hoardr.utils.RecyclerAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var loggedIn: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        initializeDisplay()
        return binding.root
    }

    private fun initializeDisplay() = with(binding){
        if(loggedIn) buttonHomeRegister.visibility = View.GONE
        else textHomeTimeGreeting.visibility = View.GONE
        /**
         * initialize home categories
         */
        // hide the view all option
        includeHomeCategoriesHeader.let {
            it.textCategoryViewAll.visibility = View.GONE
            it.imageCategoryViewAll.visibility = View.GONE
        }

        /**
         * initialize newly added items
         */
        includeHomeNewItemsHeader.let {
            it.textCategoryHeading.text = "Newly Added Items"
        }

        /**
         * initialize favourite items
         */
        includeHomeFavouriteItemsHeader.let {
            it.textCategoryHeading.text = "Favourite Items"
        }

        buttonHomeRegister.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_itemDetailFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            // load the category entries and images and display them
            val adapter = CategoriesAdapter()
            listHomeCategories.adapter = adapter
            listHomeCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            listHomeCategories.addItemDecoration(adapter.EqualSpaceItemDecoration())
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