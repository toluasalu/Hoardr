package com.zuri.pjt_95_hoardr.ui.add_items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuri.pjt_95_hoardr.databinding.FragmentSearchableListBinding
import com.zuri.pjt_95_hoardr.databinding.ItemAddItemBinding
import com.zuri.pjt_95_hoardr.models.AddItem
import com.zuri.pjt_95_hoardr.models.loadItemsData
import com.zuri.pjt_95_hoardr.utils.RecyclerAdapter

class AddItemsFragment : Fragment() {
    private lateinit var viewModel: AddItemsViewModel
    private lateinit var binding: FragmentSearchableListBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AddItemsViewModel::class.java)
        binding = FragmentSearchableListBinding.inflate(inflater, container,false)
        initializeDisplay()
        return binding.root
    }

    private fun initializeDisplay() = with(binding){
        searchItems.visibility = View.GONE
        // load the add item entries and images and display them
        listItems.adapter = AddItemsAdapter()
        listItems.layoutManager = LinearLayoutManager(requireContext())
        Log.e(javaClass.simpleName, "initializeDisplay: ${listItems.adapter} ${listItems.layoutManager}" )
    }

    inner class AddItemsAdapter: RecyclerAdapter<AddItem>(){
        override var items = loadItemsData(requireContext())
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemAddItemBinding.inflate(layoutInflater, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = holder.binding as ItemAddItemBinding
            items[position].let {
                item.textAddItemTitle.text = it.name
                item.imageAddItemIcon.setImageResource(it.image)
                item.textAddItemDescription.text = it.description
                item.root.setOnClickListener { _ ->
                    findNavController().navigate(it.navigation)
                }
            }
        }
    }
}