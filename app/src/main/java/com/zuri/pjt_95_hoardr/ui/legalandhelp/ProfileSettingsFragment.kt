package com.zuri.pjt_95_hoardr.ui.legalandhelp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.zuri.pjt_95_hoardr.R


class ProfileSettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_settings, container, false)

        val termsAndCondition = view.findViewById<CardView>(R.id.terms_condition_cardview)
        val faq = view.findViewById<CardView>(R.id.faq_cardview)
        val back = view.findViewById<ImageView>(R.id.back_option)

        // Back option
        back.setOnClickListener {
            //finish()
        }

        // Go to terms and condition
        termsAndCondition.setOnClickListener {

            val termsAndConditionsFragment: Fragment = TermsAndConditionsFragment()
            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.security_settings, termsAndConditionsFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

//            val intent = Intent(this, TermsAndConditionsFragment::class.java)
//            startActivity(intent)

        }

        // Go to faq
        faq.setOnClickListener {

            val faqFragment: Fragment = FAQsFragment()
            val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, faqFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            //startActivity(Intent(this, FaqActivity::class.java))
        }

        return view
    }

}