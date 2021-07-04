package com.zuri.pjt_95_hoardr.ui.securitysettings

import android.content.Intent
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.ui.legalandhelp.TermsAndConditionsFragment
import java.util.concurrent.Executor


class ChangePasswordFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)


        val backOption =  view.findViewById<ImageView>(R.id.back_option)
        val enterPassword = view.findViewById<TextInputEditText>(R.id.password)
        val reEnterPassword = view.findViewById<TextInputEditText>(R.id.re_enter_password)
        val save = view.findViewById<MaterialButton>(R.id.save_button)

        // Back Option
        backOption.setOnClickListener {
            //finish()
        }

        fun savePassword(){
            val enterPwd = enterPassword.text.toString().trim()
            val reEnterPwd = reEnterPassword.text.toString().trim()

            if (TextUtils.isEmpty(enterPwd)) {
                enterPassword.error = "Enter your new password"
                Toast.makeText(this.activity, "Enter your new password", Toast.LENGTH_SHORT).show()
            }

            else if (TextUtils.isEmpty(reEnterPwd)) {
                enterPassword.error = "Space must not be empty"
                Toast.makeText(this.activity, "Kindly re enter your new password", Toast.LENGTH_SHORT).show()
            }

            else if(enterPwd!= reEnterPwd) {
                Toast.makeText(this.activity, "Password must be equal", Toast.LENGTH_SHORT).show()
            }

            else {

                val passwordSuccessFragment: Fragment = PasswordChangeSuccessfullyFragment()
                val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                fragmentTransaction.replace(R.id.security_settings, passwordSuccessFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                //startActivity(Intent(this, PasswordChangedSuccessfullyActivity::class.java))

                Toast.makeText(this.activity, "Password Changed...", Toast.LENGTH_SHORT).show()
            }
        }

        // Save
        save.setOnClickListener {
            savePassword()
        }


        return view
    }


}
