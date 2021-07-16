package com.zuri.pjt_95_hoardr.utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zuri.pjt_95_hoardr.MainActivity

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 15-Jul-21 at 2:24 AM
 */
abstract class CustomViewPager<T>: Fragment(), BackPressedObserver {
    var currentScreen = 0
    val isLastScreen: Boolean
        get() = currentScreen >= screens.size
    var activeScreen: T? = null
    abstract var screens: List<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? MainActivity)?.registerBackPressedObserver(this)
    }

    override fun backPress(): Boolean {
        if(isVisible){
            return if(currentScreen == 0) false
            else {
                displayScreen(screens[--currentScreen])
                true
            }
        }
        return false
    }

    abstract fun displayScreen(screen: T)
}