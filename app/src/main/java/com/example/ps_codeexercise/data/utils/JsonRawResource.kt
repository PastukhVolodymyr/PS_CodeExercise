package com.example.ps_codeexercise.data.utils

import android.content.Context
import androidx.annotation.IdRes
import java.io.IOException

class JsonRawResource(private val context: Context) {

    fun decodeToString(@IdRes resource: Int): String? = try {
        context.resources.openRawResource(resource).bufferedReader().use {
            it.readText()
        }
    } catch (ioExc: IOException) {
        null
    }
}