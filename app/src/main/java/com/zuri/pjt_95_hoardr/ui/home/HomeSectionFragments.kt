package com.zuri.pjt_95_hoardr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.*

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 2:49 PM
 */
class ItemDetailFragment: Fragment(){
    private lateinit var binding: FragmentItemViewBinding
    private lateinit var appBarBinding: PartialAppBarBinding
    private lateinit var product: Product
    private var loggedIn: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemViewBinding.inflate(inflater, container,false)
        initializeDisplay()
        return binding.root
    }

    private fun initializeDisplay() = with(binding){
        /**
         * load items
         */
//        product.let {
//            textItemName.text = it.itemName
//            textItemPrice.text = it.price
//            textItemOwner.text = it.ownerName
//            textItemDescription.text = it.description
//        }

        appBarBinding = PartialAppBarBinding.bind(requireActivity().findViewById<View>(R.id.app_bar_image))

        binding.buttonAction.setOnClickListener {
            val dialogBinding = DialogContactOptionBinding.inflate(layoutInflater)
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(dialogBinding.root)
            val dialog = builder.create()
            dialog.show()

            dialogBinding.buttonContactCall.setOnClickListener {
                findNavController().navigate(R.id.action_itemDetailFragment_to_exerciseFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // unhide the app bar image view when the item detail fragment comes into the screen
        appBarBinding.root.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()

        // hide the app bar image when the user leaves the item detail fragment
        appBarBinding.root.visibility = View.GONE
    }
}

class ExerciseFragment: Fragment(){
    private lateinit var binding: FragmentExerciseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentExerciseBinding.inflate(inflater, container,  false)
        initiailizeDisplay()
        return binding.root
    }

    private fun initiailizeDisplay(){
        binding.buttonSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_global_home_navigation_to_successFragment)
        }
    }
}

class SuccessFragment: Fragment(){
    private lateinit var binding: FragmentSuccessBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSuccessBinding.inflate(inflater, container,  false)
        initiailizeDisplay()
        return binding.root
    }

    private fun initiailizeDisplay(){
        binding.buttonContinue.setOnClickListener {
            findNavController().popBackStack(R.id.navigation_home, false)
        }
    }
}