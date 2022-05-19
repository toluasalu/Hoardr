package com.jeffreyorazulike.hoardr

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jeffreyorazulike.hoardr.databinding.ActivityMainBinding
import com.jeffreyorazulike.hoardr.utils.BackPressedObserver
import com.jeffreyorazulike.hoardr.utils.HoardrViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_REQUEST_CODE = 0x01
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var mViewModel: HoardrViewModel
    val intentResult: MutableLiveData<Pair<Int, Intent>> = MutableLiveData()

    // fragments that want to perform an action when the back button is pressed
    private val backPressedObservers = mutableListOf<BackPressedObserver>()

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
                R.id.navigation_home, R.id.navigation_add_items
            )
        )

        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK)
            data?.let {
                intentResult.value = requestCode to it
            }
    }

    override fun onBackPressed() {
        if (backPressedObservers.filter { it.backPress() }.count() == 0)
            super.onBackPressed()
    }

    fun registerBackPressedObserver(observer: BackPressedObserver) {
        backPressedObservers.add(observer)
    }
}