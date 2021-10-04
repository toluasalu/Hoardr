package com.jeffreyorazulike.hoardr.ui.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeffreyorazulike.hoardr.databinding.FragmentSuccessBinding

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 7:23 AM
 */
class SuccessFragment: Fragment(){
    private lateinit var binding: FragmentSuccessBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSuccessBinding.inflate(inflater, container,  false)
        initializeDisplay()
        return binding.root
    }

    private fun initializeDisplay() = with(binding) {
        arguments?.let { argument ->
            SuccessFragmentArgs.fromBundle(argument).data.let {
                imageSuccess.setImageResource(it.image)
                textSuccessInfo.text = it.content
                buttonContinue.setOnClickListener { _ ->
                    findNavController().navigate(it.navigation)
                }
            }
        }
    }
}