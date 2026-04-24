package com.example.bookglowup

import android.content.Context
import android.content.Intent
object UtilObject {
    fun screenNavigation(context: Context, destination: Class<*>?)
    {
        val intent= Intent(context, destination)
        context.startActivity(intent)
    }
}