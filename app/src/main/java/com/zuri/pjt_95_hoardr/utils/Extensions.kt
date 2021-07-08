package com.zuri.pjt_95_hoardr.utils

import android.view.View
import android.widget.CheckBox
import androidx.constraintlayout.widget.Group

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 08-Jul-21 at 12:31 AM
 */
fun Group.addOnClickListener(listener: (view: View) -> Unit){
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun CheckBox.setOnCheckedListener(group: CheckBoxGroup, listener: ((view: View) -> Unit)?){
    setOnClickListener{ view ->
        if(isChecked){
            group.selected(this)
            listener?.let {
                it(view)
            }
        }
    }
}