package com.zuri.pjt_95_hoardr.security_settings

import android.content.Intent
import android.hardware.biometrics.BiometricManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Switch
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.zuri.pjt_95_hoardr.R
import java.util.concurrent.Executor

class SecuritySettingsActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var cancellationSignal: CancellationSignal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_settings)

        val biometricSwitch = findViewById<Switch>(R.id.switch_biometrics)
        val changePasswordOption = findViewById<CardView>(R.id.change_password_cardview)

        // initialize biometric
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this@SecuritySettingsActivity, executor, object: BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // Display error
                Toast.makeText(this@SecuritySettingsActivity, "Authentication Error. Kindly try again", Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@SecuritySettingsActivity, "Authentication succeeded...!", Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@SecuritySettingsActivity, "Authentication failed, try again!", Toast.LENGTH_LONG).show()
            }
        })

        // Set properties like title and description
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Always login with fingerprint authentication")
            .setNegativeButtonText("Use App Password instead")
            .build()

        // Setting up biometric
        biometricSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Allow fingerprint when clicked
                biometricPrompt.authenticate(promptInfo)

                Toast.makeText(this, "Biometric Enabled", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Biometric Disabled", Toast.LENGTH_SHORT).show()
            }
        }


        // Go to change password
        changePasswordOption.setOnClickListener {
            startActivity(Intent(this@SecuritySettingsActivity, ChangePasswordActivity::class.java))
        }

    }

    override fun onBackPressed() {

    }
}