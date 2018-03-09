package com.alemacedo.firebaseapp.extensions

import android.support.design.widget.TextInputLayout

/**
 * Created by Alexandre on 07/03/2018.
 */

fun TextInputLayout.getText() : String {

    return editText?.text.toString()
}