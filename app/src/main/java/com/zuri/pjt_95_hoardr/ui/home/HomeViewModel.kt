package com.zuri.pjt_95_hoardr.ui.home

import androidx.lifecycle.ViewModel
import com.zuri.pjt_95_hoardr.models.User

class HomeViewModel : ViewModel() {

    var user: User? = null
    var loggedIn: Boolean? = false
}