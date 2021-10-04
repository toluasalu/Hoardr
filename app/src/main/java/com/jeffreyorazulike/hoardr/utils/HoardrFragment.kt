package com.jeffreyorazulike.hoardr.utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jeffreyorazulike.hoardr.MainActivity

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 12-Jul-21 at 2:35 AM
 */
open class HoardrFragment: Fragment() {
    lateinit var appViewModel: HoardrViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? MainActivity)?.let {
            appViewModel = it.mViewModel
        }
    }
}