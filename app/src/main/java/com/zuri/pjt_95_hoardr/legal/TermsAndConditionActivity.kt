package com.zuri.pjt_95_hoardr.legal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.zuri.pjt_95_hoardr.R

class TermsAndConditionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_condition)

        val back = findViewById<ImageView>(R.id.back_option)

        // Go back
        back.setOnClickListener {
            finish()
        }
    }
}