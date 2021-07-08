package com.zuri.pjt_95_hoardr.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentSecondRegistrationBinding



/**
 * A simple [Fragment] subclass.
 * Use the [SecondRegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondRegistrationFragment : Fragment() {
    private var _binding: FragmentSecondRegistrationBinding?  = null
    private val sharedViewModel: RegistrationViewModel by activityViewModels()
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

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
       //Initialize the countries dropdown
        val listOfCountries = listOf("Nigeria", "Ethiopia", "Egypt", "DR Congo", "Tanzania")
        val countryAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfCountries)
        val countrySelection = binding.countrySelection
        (countrySelection.editText as? AutoCompleteTextView)?.setAdapter(countryAdapter)

        //Initialize the states dropdown
        val listOfStates = listOf("Lagos" ,"Abia", "Adamawa", "FCT", "Nassarawa")
        val stateAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfStates)
        val stateSelection = binding.stateSelection
        (stateSelection .editText as? AutoCompleteTextView)?.setAdapter(stateAdapter)

        //Initialize the LGA dropdown
        val listOfLGAs = listOf("Lagos Island" ,"Surulere", "Agege", "Apapa","Mushin")
        val lgaAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfLGAs)
        val lgaSelection = binding.lgaSelection
        (lgaSelection.editText as? AutoCompleteTextView)?.setAdapter(lgaAdapter)

        binding.nextButton.setOnClickListener {

            val countrySelected = (countrySelection.editText as? AutoCompleteTextView)?.text.toString()
            sharedViewModel.setCountry(countrySelected)

            val stateSelected = (stateSelection .editText as? AutoCompleteTextView)?.text.toString()
            sharedViewModel.setState(stateSelected)

            val lgaSelected = (lgaSelection.editText as? AutoCompleteTextView)?.text.toString()
            sharedViewModel.setLga(lgaSelected)

            findNavController().navigate(R.id.action_secondRegistrationFragment_to_thirdRegistrationFragment)
        }


    }

    companion object {}
}