package com.zuri.pjt_95_hoardr

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.zuri.pjt_95_hoardr.databinding.ActivityMainBinding
import com.zuri.pjt_95_hoardr.databinding.PartialAppBarBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarBinding: PartialAppBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // loads back the main theme after displaying the splash screen
        setTheme(R.style.Theme_Hoardr)
        super.onCreate(savedInstanceState)
        initializeDisplayContent()
    }

    private fun initializeDisplayContent() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        appBarBinding = binding.appBarImage
        appBarBinding.root.visibility = View.GONE

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_add_items, R.id.navigation_favourite, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    fun getAppBarBinding() = appBarBinding

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}