package com.zuri.pjt_95_hoardr.ui.add_items

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuri.pjt_95_hoardr.databinding.FragmentSearchableListBinding
import com.zuri.pjt_95_hoardr.models.fragment_initializers.AddItemsAdapter
import com.zuri.pjt_95_hoardr.ui.general.SearchableFragment

class AddItemsFragment() : SearchableFragment() {

    override fun loadContent(binding: FragmentSearchableListBinding) {
        with(binding){
            searchItems.visibility = View.GONE
            listItems.layoutManager = LinearLayoutManager(requireContext())
            listItems.adapter = AddItemsAdapter(this@AddItemsFragment)
        }
    }
}