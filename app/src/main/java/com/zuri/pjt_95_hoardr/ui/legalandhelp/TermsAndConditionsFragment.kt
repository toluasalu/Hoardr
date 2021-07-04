package com.zuri.pjt_95_hoardr.ui.legalandhelp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.zuri.pjt_95_hoardr.R


class TermsAndConditionsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false)

        val back = view.findViewById<ImageView>(R.id.back_option)

        // Go back
        back.setOnClickListener {
            //finish()
        }


        return view
    }

}