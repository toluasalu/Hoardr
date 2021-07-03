package com.zuri.pjt_95_hoardr.security_settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.zuri.pjt_95_hoardr.R
import com.zuri.pjt_95_hoardr.legal.LegalActivity

class PasswordChangedSuccessfullyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_changed_successfully)

        val continueBtn = findViewById<MaterialButton>(R.id.continue_button)

        // Continue Option
        continueBtn.setOnClickListener {
            startActivity(Intent(this, LegalActivity::class.java))
        }
    }

    // On back press
    override fun onBackPressed() {
        Toast.makeText(this, "You cannot go back, click on continue", Toast.LENGTH_SHORT).show()
    }
}