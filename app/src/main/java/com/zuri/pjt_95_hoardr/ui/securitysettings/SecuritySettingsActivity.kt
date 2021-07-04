package com.zuri.pjt_95_hoardr.ui.securitysettings

import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricPrompt
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CancellationSignal
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.ui.legalandhelp.TermsAndConditionsFragment
import java.util.concurrent.Executor

class SecuritySettingsActivity : Fragment() {

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val changePasswordOption = view.findViewById<CardView>(R.id.change_password_cardview)



        // Go to change password
        changePasswordOption.setOnClickListener {

            val changePasswordFragment: Fragment = ChangePasswordFragment()
            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.security_settings, changePasswordFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


            //startActivity(Intent(this@SecuritySettingsActivity, ChangePasswordActivity::class.java))
        }

    return view

    }

}
