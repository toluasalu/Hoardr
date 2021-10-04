package com.jeffreyorazulike.hoardr.ui.bottom_sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeffreyorazulike.hoardr.databinding.DialogExchangeOptionBinding
import com.jeffreyorazulike.hoardr.utils.ImageAdapter

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 13-Jul-21 at 5:26 PM
 */
class ExchangeOptionSheet: BottomSheetDialogFragment() {
    private lateinit var binding: DialogExchangeOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogExchangeOptionBinding.inflate(inflater, container, false)
        initializeContent()
        return binding.root
    }

    private fun initializeContent() = with(binding) {
        val maxNumberOfImages = 8
        listExchangeOptions.adapter = ImageAdapter(this@ExchangeOptionSheet, maxNumberOfImages)
        listExchangeOptions.layoutManager = GridLayoutManager(requireContext(), 3)
        buttonProceed.setOnClickListener { this@ExchangeOptionSheet.dismiss() }
    }
}