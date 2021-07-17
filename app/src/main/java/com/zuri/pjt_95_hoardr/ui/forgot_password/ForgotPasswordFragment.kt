package com.zuri.pjt_95_hoardr.ui.forgot_password

import android.graphics.Typeface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.databinding.ForgotPasswordFragmentBinding

class ForgotPasswordFragment : Fragment() {

    companion object {}
    private var _binding: ForgotPasswordFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =  ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)
        _binding = ForgotPasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}