package com.jeffreyorazulike.hoardr.utils

import android.widget.CheckBox
import com.jeffreyorazulike.hoardr.ui.home.ExerciseFragmentDirections
import kotlinx.coroutines.newFixedThreadPoolContext

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

    private fun isPartOfGroup(checkbox: CheckBox): Boolean{
        if (checkbox !in checkboxes)
            throw IllegalArgumentException("This checkbox is not part of this group")
        return true
    }

    fun select(checkbox: CheckBox) {
        isPartOfGroup(checkbox)
        checkboxes.asSequence().filter{
            it != checkbox
        }.forEach {
            it.isChecked = false
        }
    }

    fun getCheckedOr(checkbox: CheckBox): CheckBox{
        isPartOfGroup(checkbox)
        val checked = checkboxes.asSequence().filter {
            it.isChecked
        }.toList()
        return if(checked.isEmpty()) checkbox else checked.first()
    }
}