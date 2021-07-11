package com.zuri.pjt_95_hoardr.ui.login

import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.FragmentLoginBinding
import com.zuri.pjt_95_hoardr.models.User
import java.util.regex.Pattern


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceStkate: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()

        // account for testing
        binding.emailField.editText?.setText("i@i.com")
        binding.passwordField.editText?.setText("i")

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
                outlineEmailField )

        }

        //set on click listeners for the forgot password and register link
        binding.registerTodayLink.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }

        binding.forgotPasswordLink.setOnClickListener {
            findNavController().navigate(R.id.forgotPasswordFragment)
        }

        binding.loginBtn.setOnClickListener {
            verifyUser()
        }


    }

    private fun verifyUser() {
        db.collection("users")
            .whereEqualTo("email", binding.emailField.editText?.text.toString().lowercase())
            .whereEqualTo("password", binding.passwordField.editText?.text.toString())
            .get().addOnSuccessListener {
                if(it.documents.isNotEmpty()){
                    it.documents.first().toObject(User::class.java)?.let{ user ->
                        Snackbar.make(requireView(), "Welcome back, ${user.first}", Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationHome(user))
                    }
                }else{
                    Snackbar.make(requireView(), "Account not found", Snackbar.LENGTH_SHORT).show()
                }

            }.addOnFailureListener {
                Snackbar.make(requireView(), "${it.message}", Snackbar.LENGTH_SHORT).show()
            }
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