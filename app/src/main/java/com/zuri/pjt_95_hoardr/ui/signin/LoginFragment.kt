package com.zuri.pjt_95_hoardr.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentLoginBinding
import com.zuri.pjt_95_hoardr.models.User
import com.zuri.pjt_95_hoardr.utils.HoardrFragment
import com.zuri.pjt_95_hoardr.utils.validateEmailAddress
import com.zuri.pjt_95_hoardr.utils.validatePassword

class LoginFragment : HoardrFragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceStkate: Bundle?
    ): View {
        // Inflate the layout for this fragment
        db = Firebase.firestore
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            emailText = textinputEmail.editText!!
            passwordText = textinputPassword.editText!!

            // account for testing
            emailText.setText("admin@hoardr.com")
            passwordText.setText("l0W@rcase")
            // validate the email field when it looses focus
            emailText.setOnFocusChangeListener { _, focus ->
                if(!focus) textinputEmail.validateEmailAddress(textEmailRequirements)
            }
            passwordText.setOnFocusChangeListener { _, focus ->
                if(!focus) textinputPassword.validatePassword(binding.textPasswordRequirement)
            }
            //set on click listeners for the forgot password and register link
            textRegisterNow.setOnClickListener {
                findNavController().navigate(R.id.registrationFragment)
            }
            textForgotPassword.setOnClickListener {
                findNavController().navigate(R.id.forgotPasswordFragment)
            }
            buttonLogin.setOnClickListener {
                buttonLogin.startAnimation()
                verifyUser()
            }
        }
    }

    private fun verifyUser() = with(binding){
        if (textinputEmail.validateEmailAddress(textEmailRequirements) && textinputPassword.validatePassword(textPasswordRequirement))
            db.collection("users")
                .whereEqualTo("email", emailText.text.toString().lowercase())
                .whereEqualTo("password", passwordText.text.toString())
                .get().addOnSuccessListener {
                    if(it.documents.isNotEmpty()){
                        it.documents.first().toObject(User::class.java)?.let{ user ->
                            Snackbar.make(requireView(), "Welcome back, ${user.first}", Snackbar.LENGTH_SHORT).show()
                            appViewModel.user = user
                            findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                        }
                    }else{
                        Snackbar.make(requireView(), "Account not found", Snackbar.LENGTH_SHORT).show()
                        buttonLogin.revertAnimation()
                    }

                }.addOnFailureListener {
                    Snackbar.make(requireView(), "${it.message}", Snackbar.LENGTH_SHORT).show()
                    buttonLogin.revertAnimation()
                }
        else {
            buttonLogin.revertAnimation()
        }
    }
}