package com.kotlin.trifork.marvelapp.common.utils.helper

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityHelper {
    fun isConnectedToNetwork(context: Context): Boolean {
        var connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false

        if (connectivityManager != null) {
            val activeNetwork = connectivityManager.activeNetwork
            isConnected = activeNetwork != null
        }
        return isConnected
    }
}