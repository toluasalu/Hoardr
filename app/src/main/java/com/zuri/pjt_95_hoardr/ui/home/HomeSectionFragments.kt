package com.zuri.pjt_95_hoardr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.DialogContactOptionBinding
import com.zuri.pjt_95_hoardr.databinding.FragmentExerciseBinding
import com.zuri.pjt_95_hoardr.databinding.FragmentItemViewBinding
import com.zuri.pjt_95_hoardr.models.fragment_initializers.SuccessModel

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 2:49 PM
 */
class ItemDetailFragment: Fragment(){
    private lateinit var binding: FragmentItemViewBinding
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
}

class ExerciseFragment: Fragment(){
    private lateinit var binding: FragmentExerciseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentExerciseBinding.inflate(inflater, container,  false)
        initializeDisplay()
        return binding.root
    }

    private fun initializeDisplay(){
        binding.buttonSubmit.setOnClickListener {
            findNavController().navigate(
                ExerciseFragmentDirections.actionExerciseFragmentToSuccessFragment(
                    SuccessModel(getString(R.string.success_exercise),
                    R.drawable.vector_successful_test, R.id.navigation_home)
                )
            )
        }
    }
}