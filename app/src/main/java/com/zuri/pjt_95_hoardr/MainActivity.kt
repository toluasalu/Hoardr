package com.zuri.pjt_95_hoardr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.zuri.pjt_95_hoardr.databinding.ActivityMainBinding
import com.zuri.pjt_95_hoardr.utils.HoardrViewModel

class MainActivity : AppCompatActivity() {

    companion object{
        const val IMAGE_REQUEST_CODE = 0x01
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var mViewModel: HoardrViewModel
    val intentResult: MutableLiveData<Pair<Int, Intent>> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        // loads back the main theme after displaying the splash screen
        setTheme(R.style.Theme_Hoardr)
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(HoardrViewModel::class.java)
        initializeDisplayContent()
    }

    private fun initializeDisplayContent() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

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

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK)
            data?.let {
                intentResult.value = requestCode to it
            }
    }
}