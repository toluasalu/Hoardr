package com.zuri.pjt_95_hoardr.ui.securitysettings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.ui.legalandhelp.ProfileSettingsFragment
import com.zuri.pjt_95_hoardr.ui.legalandhelp.TermsAndConditionsFragment


class PasswordChangeSuccessfullyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_password_change_successfully, container, false)

        val continueBtn = view.findViewById<MaterialButton>(R.id.continue_button)

        // Continue Option
        continueBtn.setOnClickListener {

            val profileSettingsFragment: Fragment = ProfileSettingsFragment()
            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.security_settings, profileSettingsFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            //startActivity(Intent(this, LegalActivity::class.java))
        }

        return view
    }

}