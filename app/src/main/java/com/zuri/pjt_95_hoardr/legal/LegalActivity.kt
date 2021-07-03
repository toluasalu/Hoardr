package com.zuri.pjt_95_hoardr.legal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.zuri.pjt_95_hoardr.R

class LegalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legal)

        val termsAndCondition = findViewById<CardView>(R.id.terms_condition_cardview)
        val faq = findViewById<CardView>(R.id.faq_cardview)
        val back = findViewById<ImageView>(R.id.back_option)

        // Back option
        back.setOnClickListener {
            finish()
        }

        // Go to terms and condition
        termsAndCondition.setOnClickListener {
            startActivity(Intent(this, TermsAndConditionActivity::class.java))
        }

        // Go to faq
        faq.setOnClickListener {
            startActivity(Intent(this, FaqActivity::class.java))
        }
    }

    override fun onBackPressed() {

    }
}