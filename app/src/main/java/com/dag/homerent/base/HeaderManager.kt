package com.dag.homerent.base

import javax.inject.Inject

class HeaderManager @Inject constructor() {
    val headers = hashMapOf<String, String>()

    fun putHeader(key: String, value: String) {
        headers[key] = value
    }

    fun removeHeader(key: String) {
        headers.remove(key)
    }
}