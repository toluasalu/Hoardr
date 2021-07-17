package com.zuri.pjt_95_hoardr.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentRegistrationBinding
import com.zuri.pjt_95_hoardr.models.User
import com.zuri.pjt_95_hoardr.utils.forEach


class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        with(binding){
//            screens = listOf(frGroupFirstScreen, frGroupSecondScreen, frGroupThirdScreen)
//            displayScreen(screens[currentScreen++])
//        }
        initializeContent()
        binding.buttonFrNext.setOnClickListener{
//            if (isLastScreen) registerUser() else displayScreen(screens[currentScreen++])
            registerUser()
        }
    }

    private fun initializeContent(){
        //Initialize the countries dropdown
        val listOfCountries = listOf("Nigeria", "Ethiopia", "Egypt", "DR Congo", "Tanzania")
        val countryAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfCountries)
        (binding.textinputFrCountry.editText as? AutoCompleteTextView)?.setAdapter(countryAdapter)

        //Initialize the states dropdown
        val listOfStates = listOf("Lagos" ,"Abia", "Adamawa", "FCT", "Nassarawa")
        val stateAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfStates)
        (binding.textinputFrState.editText as? AutoCompleteTextView)?.setAdapter(stateAdapter)

        //Initialize the LGA dropdown
        val listOfLGAs = listOf("Lagos Island" ,"Surulere", "Agege", "Apapa","Mushin")
        val lgaAdapter = ArrayAdapter(this.requireContext(), R.layout.list_item, listOfLGAs)
        (binding.textinputFrLga.editText as? AutoCompleteTextView)?.setAdapter(lgaAdapter)
    }

    private fun registerUser(){

        // checks if all the fields are filled
        fun areRequiredFieldsFilled(): Boolean{
            var response = true
            binding.frGroupRequiredFields.forEach {
                if((it as? TextInputLayout)?.editText?.text.isNullOrBlank()){
                    Snackbar.make(requireView(), "One or more fields hkave not been filled", Snackbar.LENGTH_SHORT).show()
                    it.requestFocus()
                    response = false
                    return@forEach
                }
            }
            return response
        }
        with(binding){
            if(areRequiredFieldsFilled() && passwordsMatch(textinputFrPassword, textinputFrConfirmPassword)){
                binding.buttonFrNext.startAnimation()
                val user = User(
                    first = textinputFrFirstName.editText?.text.toString(),
                    last = textinputFrLastName.editText?.text.toString(),
                    email = textinputFrEmail.editText?.text.toString(),
                    password = textinputFrPassword.editText?.text.toString(),
                    phoneNumber = textinputFrPhoneNumber.editText?.text.toString(),
                    country = textinputFrCountry.editText?.text.toString(),
                    state = textinputFrState.editText?.text.toString(),
                    lga = textinputFrLga.editText?.text.toString(),
                )
                // check if the user already exists
                Firebase.firestore.collection("users")
                    .whereEqualTo("email", textinputFrEmail.editText?.text.toString())
                    .whereEqualTo("password", textinputFrPassword.editText?.text.toString())
                    .get().addOnSuccessListener { querysnapshot ->
                        if(querysnapshot.isEmpty){
                            Firebase.firestore.collection("users").add(user)
                                .addOnSuccessListener {
                                    goToLogin()
                            }.addOnFailureListener{
                                    Snackbar.make(requireView(),"We had a problem registering you", Snackbar.LENGTH_SHORT).show()
                                }
                        }else goToLogin()
                    }

            }
        }

    }

//    override fun displayScreen(screen: Group) {
//        with(binding){
//            textRegistrationProgress.text = "${currentScreen + 1} / ${screens.size}"
//            buttonFrNext.text = if(isLastScreen) "Register" else "Next"
//            activeScreen?.visibility = View.GONE
//            screen.visibility = View.VISIBLE
//            activeScreen = screen
//        }
//    }

    private fun passwordsMatch(password: TextInputLayout, confirmPassword: TextInputLayout): Boolean{
        val match = password.editText!!.text.toString() == confirmPassword.editText!!.text.toString()
        if(!match) Snackbar.make(requireView(),"Passwords don't match", Snackbar.LENGTH_SHORT).show()
        return match
    }

    private fun goToLogin() {
        binding.buttonFrNext.revertAnimation()
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }
}