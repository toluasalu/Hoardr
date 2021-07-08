package com.zuri.pjt_95_hoardr

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EmailAddress : Fragment() {

    companion object {
        fun newInstance() = EmailAddress()
    }

    private lateinit var viewModel: EmailAddressViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.email_address_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmailAddressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}