package com.zuri.pjt_95_hoardr.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentLoginBinding
import java.util.regex.Pattern


/**
 * A simple [Fragment] subclass.

 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private lateinit var db: FirebaseFirestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Access a Cloud Firestore instance
        db = Firebase.firestore


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val outlineEmailField = binding.emailField
        val outlinePasswordField = binding.passwordField

        val passwordRequirementTextView = binding.passwordRequirementText
        // Get input text
        val emailText = outlineEmailField.editText?.text.toString()
        val passwordText = outlinePasswordField.editText?.text.toString()

        outlineEmailField.editText?.doOnTextChanged { emailText, _, _, _ ->
            // Respond to input text change
            validateEmailInput(emailText, outlineEmailField)

        }

        outlinePasswordField.editText?.doOnTextChanged { passwordText, _, _, _ ->
            // check for pattern
            validatePassword(
                passwordText,
                outlinePasswordField,
                passwordRequirementTextView,
                outlineEmailField
            )

        }

        //set on click listeners for the forgot password and register link
        binding.registerTodayLink.setOnClickListener {
            registerUser(it)
        }

        binding.forgotPasswordLink.setOnClickListener {
            userForgotPassword(it)
        }

        binding.loginBtn.setOnClickListener {
            verifyUser()
        }


    }

    private fun verifyUser() {
        val enteredEmail = binding.emailField.editText?.text.toString()
        val enteredUserPassword = binding.passwordField.editText?.text.toString()
        val applicationContext = this.requireContext()
        val handler = Handler(Looper.getMainLooper())
        // val action = LoginFragmentDirections.actionLoginFragmentToNavigationHome()

        db.collection("users")
            .whereEqualTo("password", enteredUserPassword)
            .whereEqualTo("email", enteredEmail)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("LoginFragment", "${document.id} => ${document.data["first"]}")
                }

                handler.post {
                    Toast.makeText(
                        applicationContext,
                        "Login was successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.navigation_home)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("LoginFragment", "Error getting documents: ", exception)
            }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun registerUser(view: View) {
        view.findNavController().navigate(R.id.registrationFragment)
    }

    private fun userForgotPassword(view: View) {
        view.findNavController().navigate(R.id.forgotPasswordFragment)
    }


    private fun validatePassword(
        passwordText: CharSequence?,
        outlinePasswordField: TextInputLayout,
        passwordRequirementTextView: TextView,
        outlineEmailField: TextInputLayout
    ) {
        val uppercase: Pattern = Pattern.compile("[A-Z]")
        val lowercase: Pattern = Pattern.compile("[a-z]")
        val specialChar = Pattern.compile("\\@")
        val digit: Pattern = Pattern.compile("[0-9]")


        if (!lowercase.matcher(passwordText).find() || !uppercase.matcher(passwordText)
                .find() || !digit.matcher(passwordText)
                .find() || !specialChar.matcher(passwordText)
                .find() || passwordText?.length!! < 8 || passwordText.length > 10
        ) {
            outlinePasswordField.error =
                "The password you entered is wrong, kindly confirm if you\n" +
                        "missed any of the requirements below."
            //Make Password Requirements visible
            passwordRequirementTextView.visibility = View.VISIBLE

        } else {
            outlinePasswordField.error = null
            outlineEmailField.boxBackgroundColor = Color.TRANSPARENT
            outlinePasswordField.boxStrokeColor = Color.parseColor("#34eb77")
            //Make Password Requirements disappear
            passwordRequirementTextView.visibility = View.GONE
        }
    }

    private fun validateEmailInput(
        emailText: CharSequence?,
        outlineEmailField: TextInputLayout
    ) {
        val emailRequirementTextView = binding.emailRequirementsText

        if (Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            outlineEmailField.error = null
            outlineEmailField.boxStrokeColor = Color.parseColor("#34eb77")
            outlineEmailField.boxBackgroundColor = Color.TRANSPARENT
            outlineEmailField.setEndIconDrawable(R.drawable.ic_login_success)
            emailRequirementTextView.visibility = View.GONE


        } else {
            outlineEmailField.error =
                "The email address you entered is wrong, kindly confirm if it\n" +
                        "follows the email requirements"
            //Make email requirements visible
            emailRequirementTextView.visibility = View.VISIBLE
        }
    }


    companion object {}
}