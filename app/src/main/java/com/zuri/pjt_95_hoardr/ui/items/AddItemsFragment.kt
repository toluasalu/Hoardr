package com.zuri.pjt_95_hoardr.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuri.pjt_95_hoardr.databinding.FragmentSearchableListBinding
import com.zuri.pjt_95_hoardr.models.fragment_initializers.AddItemsAdapter

class AddItemsFragment: Fragment() {

    private lateinit var binding: FragmentSearchableListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchableListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            searchItems.visibility = View.GONE
            listItems.layoutManager = LinearLayoutManager(requireContext())
            listItems.adapter = AddItemsAdapter(this@AddItemsFragment)
        }
    }
}