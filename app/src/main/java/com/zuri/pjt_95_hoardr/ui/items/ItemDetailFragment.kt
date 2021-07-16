package com.zuri.pjt_95_hoardr.ui.items

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zuri.pjt_95_hoardr.MainActivity
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentItemViewBinding
import com.zuri.pjt_95_hoardr.models.Item
import com.zuri.pjt_95_hoardr.models.User
import com.zuri.pjt_95_hoardr.ui.bottom_sheets.ContactOptionSheet
import com.zuri.pjt_95_hoardr.ui.bottom_sheets.ExchangeOptionSheet
import com.zuri.pjt_95_hoardr.utils.loadImage

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 13-Jul-21 at 2:21 PM
 */
class ItemDetailFragment : Fragment(){
    private lateinit var binding: FragmentItemViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemViewBinding.inflate(inflater, container,false)
        initializeContent()
        return binding.root
    }

    private fun initializeContent() = with(binding){
        fun setup(user: User?, item: Item){
            (requireActivity() as? MainActivity)?.supportActionBar?.title = item.title
            textItemName.text = item.title
            when(item.price){
                0 -> {
                    textItemPrice.text = "Free"
                    buttonAction.text = "Take a quick exercise"
                    buttonAction.setOnClickListener { user?.phoneNumber?.let { phone ->
                        ContactOptionSheet(
                            phone
                        ).show(childFragmentManager, "contact")
                    } }
                }
                -1 -> {
                    textItemPrice.text = "Exchange"
                    buttonAction.text = "Take a quick exercise"
                    buttonAction.setOnClickListener { ExchangeOptionSheet().show(childFragmentManager, "exchange") }
                }
                else -> {
                    textItemPrice.text = item.price.toString().format()
                    buttonAction.text = "Talk to owner"
                    buttonAction.setOnClickListener { findNavController().navigate(R.id.action_itemDetailFragment_to_exerciseFragment) }
                }
            }
            textItemOwner.text = "${user?.first} ${user?.last}"
            textItemDescription.text = item.description
            includeItemImage.image.loadImage(Uri.parse(item.image), true)
        }
        arguments?.let { bundle ->
            val item = ItemDetailFragmentArgs.fromBundle(bundle).item
            val user = ItemDetailFragmentArgs.fromBundle(bundle).user
            setup(user, item)
        }
    }
}