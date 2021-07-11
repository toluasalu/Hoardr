package com.zuri.pjt_95_hoardr.ui.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.zuri.pjt_95_hoardr.databinding.FragmentSearchableListBinding

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 11-Jul-21 at 9:08 PM
 */
open class SearchableFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchableListBinding.inflate(inflater, container,false)
        loadContent(binding)
        return binding.root
    }

    open fun loadContent(binding: FragmentSearchableListBinding) = with(binding){
        arguments?.let { args ->
            SearchableFragmentArgs.fromBundle(args).data?.let {
                searchItems.visibility = if(it.searchIsVisible) View.VISIBLE else View.GONE
                // load the item entries
                listItems.adapter = it.adapter
                listItems.layoutManager = GridLayoutManager(requireContext(), it.numberOfColumns)
            }
        }
    }
}