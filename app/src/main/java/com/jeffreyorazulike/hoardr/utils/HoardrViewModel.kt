package com.jeffreyorazulike.hoardr.utils

import androidx.lifecycle.ViewModel
import com.jeffreyorazulike.hoardr.models.Item
import com.jeffreyorazulike.hoardr.models.User

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 12-Jul-21 at 2:19 AM
 */
class HoardrViewModel : ViewModel() {
    var user: User? = null
    var loggedIn: Boolean = false
    var items: List<Item>? = null
}