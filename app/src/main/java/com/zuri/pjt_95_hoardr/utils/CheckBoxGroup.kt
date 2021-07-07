package com.zuri.pjt_95_hoardr.utils

import android.widget.CheckBox

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 07-Jul-21 at 7:25 AM
 */
class CheckBoxGroup(vararg val cs: CheckBox) {
    val checkBoxes: MutableList<CheckBox> = cs.toMutableList()

    fun add(checkBox: CheckBox) = checkBoxes.add(checkBox)

    fun setOnClickListener(checkBox: CheckBox, onClick: (c: CheckBox) -> Unit) {
        checkBoxes.asSequence().filter{
            it != checkBox
        }.forEach {
            it.isSelected = false
        }
        onClick(checkBox)
    }
}