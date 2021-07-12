package com.zuri.pjt_95_hoardr.utils

import androidx.lifecycle.ViewModel
import com.zuri.pjt_95_hoardr.models.Item
import com.zuri.pjt_95_hoardr.models.User

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 12-Jul-21 at 2:19 AM
 */
class HoardrViewModel : ViewModel() {
    var user: User? = null
    var loggedIn: Boolean = false
    var items: List<Item>? = null
}