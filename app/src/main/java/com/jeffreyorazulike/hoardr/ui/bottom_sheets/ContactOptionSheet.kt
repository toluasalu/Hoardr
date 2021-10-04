package com.jeffreyorazulike.hoardr.ui.bottom_sheets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeffreyorazulike.hoardr.databinding.DialogContactOptionBinding

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 13-Jul-21 at 5:16 PM
 */
class ContactOptionSheet(private val phoneNumber: String): BottomSheetDialogFragment() {
    private lateinit var binding: DialogContactOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogContactOptionBinding.inflate(inflater, container, false)
        initializeContent()
        return binding.root
    }

    private fun initializeContent() = with(binding) {
        buttonContactCall.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
            }catch (e: SecurityException){
                val message = "An error occured while opening the phone dialer"
                Log.e(TAG, "initializeContent: $message")
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
            this@ContactOptionSheet.dismiss()
        }
        buttonContactMessage.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.putExtra("sms_body", "Body")
            smsIntent.data = Uri.parse("sms:$phoneNumber")
            startActivity(smsIntent)
            this@ContactOptionSheet.dismiss()
        }
    }

    companion object {
        val TAG = ContactOptionSheet::class.simpleName.toString()
    }
}