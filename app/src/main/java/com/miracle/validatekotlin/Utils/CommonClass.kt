package com.miracle.validatekotlin.Utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast

class CommonClass {
    companion object {
        private var showLog = true
    }

    fun ShowToast(context: Context, msg: String) {
        val myToast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
        myToast.show()
    }

    fun ShowLoge(tag: String, msg: String) {
        if (showLog) {
            Log.e(TAG, tag + ":  " + msg)
        }
    }
}

