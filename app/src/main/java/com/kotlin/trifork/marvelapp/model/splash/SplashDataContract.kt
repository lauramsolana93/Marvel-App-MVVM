package com.kotlin.trifork.marvelapp.model.splash

import android.content.Context
import androidx.lifecycle.MutableLiveData

interface SplashDataContract {
    interface Repository {
        val connection: MutableLiveData<Boolean>
        fun checkConnection(context: Context)
    }
}