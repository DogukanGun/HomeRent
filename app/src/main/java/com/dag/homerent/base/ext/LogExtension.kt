package com.dag.homerent.base.ext

import android.util.Log

inline val <reified T> T.TAG:String get() = T::class.java.name

inline fun <reified T> T.logDebug(message:Any?,logTag:String = "LogDebug ${this.TAG}",isError:Boolean = false){
    tryCatch(
        {
            if (isError) Log.e(logTag,"$message") else Log.d(logTag,"$message")
        }
    )
}