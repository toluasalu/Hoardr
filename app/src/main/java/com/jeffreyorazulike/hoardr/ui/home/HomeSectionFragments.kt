package com.jeffreyorazulike.hoardr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeffreyorazulike.hoardr.R
import com.jeffreyorazulike.hoardr.databinding.FragmentExerciseBinding
import com.jeffreyorazulike.hoardr.models.fragment_initializers.SuccessModel

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 06-Jul-21 at 2:49 PM
 */

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