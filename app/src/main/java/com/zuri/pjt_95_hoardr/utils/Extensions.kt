package com.zuri.pjt_95_hoardr.utils

import android.app.Activity
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.constraintlayout.widget.Group

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 08-Jul-21 at 12:31 AM
 */
fun Group.forEach(action: (view: View) -> Unit){
    referencedIds.forEach { id ->
        action(rootView.findViewById<View>(id))
    }
}

fun CheckBox.setOnCheckedListener(group: CheckBoxGroup, listener: ((view: View) -> Unit)?){
    setOnClickListener{ view ->
        if(isChecked){
            group.select(this)
            listener?.let {
                it(view)
            }
        }
    }
}

fun ImageView.loadImage(uri: Uri, activity: Activity){
    setImageBitmap(
        BitmapFactory.decodeStream(
            activity.contentResolver.openInputStream(uri)
        )
    )
}