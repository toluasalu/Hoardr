package com.zuri.pjt_95_hoardr.utils

import android.widget.CheckBox

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 7:25 AM
 */
class CheckBoxGroup(vararg val checkboxes: CheckBox) {

    init{
        checkboxes.forEach {
            it.setOnCheckedListener(this, null)
        }
    }

    fun selected(checkbox: CheckBox) {
        checkboxes.asSequence().filter{
            it != checkbox
        }.forEach {
            it.isChecked = false
        }
    }
}