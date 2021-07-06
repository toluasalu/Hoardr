package com.zuri.pjt_95_hoardr.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentSecondRegistrationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondRegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondRegistrationFragment : Fragment() {
    private var _binding: FragmentSecondRegistrationBinding?  = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondRegistrationBinding.inflate(inflater,container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       //Initialize the countries dropdown
        val listOfCountries = listOf("Nigeria", "Ethiopia", "Egypt", "DR Congo", "Tanzania", "South Africa")
        val countryAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfCountries)
        (binding.countrySelection.editText as? AutoCompleteTextView)?.setAdapter(countryAdapter)

        //Initialize the states dropdown
        val listOfStates = listOf("Lagos" ,"Abia", "Adamawa", "Akwa Ibom", "Bauchi", "Bayelsa", "Benue")
        val stateAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfStates)
        (binding.stateSelection.editText as? AutoCompleteTextView)?.setAdapter(stateAdapter)

        //Initialize the LGA
        val listOfLGAs = listOf("Lagos Island" ,"Surulere", "Agege", "Apapa", "Ikorodu", "Mushin")
        val lgaAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfLGAs)
        (binding.lgaSelection.editText as? AutoCompleteTextView)?.setAdapter(lgaAdapter)

        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_secondRegistrationFragment_to_thirdRegistrationFragment)
        }


    }

    companion object {}
}