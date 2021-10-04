package com.jeffreyorazulike.hoardr.ui.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeffreyorazulike.hoardr.databinding.FragmentSearchableListBinding
import com.jeffreyorazulike.hoardr.utils.HoardrFragment
import com.jeffreyorazulike.hoardr.utils.ItemAdapter

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 15-Jul-21 at 10:45 AM
 */
class FavouritesFragment: HoardrFragment() {
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
            requireActivity().title = "Favourites"
            listItems.adapter = ItemAdapter(appViewModel){ item, user ->
                FavouritesFragmentDirections.actionFavouritesFragmentToItemDetailFragment(
                    item, user
                )
            }
        }
    }
}