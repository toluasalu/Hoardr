package com.jeffreyorazulike.hoardr.utils

import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.jeffreyorazulike.hoardr.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.util.regex.Pattern

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

/**
 * @param imageLocation The Uri or String representing the location
 * of the string in the given medium,
 * @param fromFirebase Is the image to be gotten from firebase or the users location n
 * */
fun ImageView.loadImage(imageLocation: Uri, fromFirebase: Boolean = false){
    val TAG = javaClass.simpleName
    fun showImageOnImageView(uri: Uri)  {
        val view = this
        CoroutineScope(Dispatchers.Main).launch {
            Glide.with(context).load(uri).into(view)
        }
    }

    fun getImageFromFirebase() {
        val imageName = imageLocation.toString()
        // get a reference to the
        // external storage data directory
        // where the image will be cached
        val storageDir = File(context.externalCacheDir, "items")
        // create the directory if it doesn't exist
        if(!storageDir.exists()) storageDir.mkdirs()

        Log.d(TAG, "loadImage: directory: $storageDir imageUri: $imageName")
        // get a reference to the image file
        val localFile = File(storageDir, imageName.substringAfter("/"))
        // Only download the file from firebase cloud storage
        // if it doesn't exist to save bandwidth
        if(localFile.exists())
            showImageOnImageView(Uri.fromFile(localFile))
        else {
            localFile.createNewFile()
            Firebase.storage.reference.child(imageName).getFile(localFile)
                .addOnSuccessListener {
                    showImageOnImageView(Uri.fromFile(localFile))
                }.addOnFailureListener{
                    Log.e(TAG, "loadImage: Image couldn't load")
                }
        }
    }


    CoroutineScope(Dispatchers.IO).launch{
        try{
            if(fromFirebase)
                getImageFromFirebase()
            else
                showImageOnImageView(imageLocation)
        }catch(exception: IOException){
            Log.e(TAG, "loadImage: ${exception.message}")
        }
    }
}

fun TextInputLayout.validatePassword(requirements: View): Boolean {
    val passwordText = editText!!.text.toString()
    val password = Pattern.compile("[a-z]+[A-Z]+//d+//w+//W+")

    return if(true){
        error = null
        boxBackgroundColor = Color.TRANSPARENT
        boxStrokeColor = Color.parseColor("#34eb77")
        //Make Password Requirements disappear
        requirements.visibility = View.GONE
        true
    }else{
        error =
            "The password you entered is wrong, kindly confirm if you\n" +
                    "missed any of the requirements below."
        //Make Password Requirements visible
        requirements.visibility = View.VISIBLE
        false
    }
}

fun TextInputLayout.validateEmailAddress(requirements: View): Boolean {
    val emailText = editText!!.text.toString()
    return if (Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
        error = null
        boxStrokeColor = Color.parseColor("#34eb77")
        boxBackgroundColor = Color.TRANSPARENT
        setEndIconDrawable(R.drawable.ic_login_success)
        requirements.visibility = View.GONE
        true
    } else {
        error =
            "The email address you entered is wrong, kindly confirm if it\n" +
                    "follows the email requirements"
        //Make email requirements visible
        requirements.visibility = View.VISIBLE
        false
    }
}