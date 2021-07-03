package com.zuri.pjt_95_hoardr.security_settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.zuri.pjt_95_hoardr.R

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val backOption =  findViewById<ImageView>(R.id.back_option)
        val enterPassword = findViewById<TextInputEditText>(R.id.password)
        val reEnterPassword = findViewById<TextInputEditText>(R.id.re_enter_password)
        val save = findViewById<MaterialButton>(R.id.save_button)

        // Back Option
        backOption.setOnClickListener {
            finish()
        }

        fun savePassword(){
            val enterPwd = enterPassword.text.toString().trim()
            val reEnterPwd = reEnterPassword.text.toString().trim()

            if (TextUtils.isEmpty(enterPwd)) {
                enterPassword.error = "Enter your new password"
                Toast.makeText(this, "Enter your new password", Toast.LENGTH_SHORT).show()
            }

            else if (TextUtils.isEmpty(reEnterPwd)) {
                enterPassword.error = "Space must not be empty"
                Toast.makeText(this, "Kindly re enter your new password", Toast.LENGTH_SHORT).show()
            }

            else if(enterPwd!= reEnterPwd) {
                Toast.makeText(this, "Password must be equal", Toast.LENGTH_SHORT).show()
            }

            else {
                startActivity(Intent(this, PasswordChangedSuccessfullyActivity::class.java))
                Toast.makeText(this, "Password Changed in progress...", Toast.LENGTH_SHORT).show()
            }
        }

        // Save
        save.setOnClickListener {
            savePassword()
        }

    }
}