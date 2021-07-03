package com.zuri.pjt_95_hoardr.legal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.textview.MaterialTextView
import com.ms.square.android.expandabletextview.ExpandableTextView
import com.zuri.pjt_95_hoardr.R

class FaqActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val back = findViewById<ImageView>(R.id.back_option)


        // back option
        back.setOnClickListener {
            finish()
        }

    }
}