package com.kotlin.trifork.marvelapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.trifork.marvelapp.model.splash.SplashDataContract

class SplashViewModel constructor(
    private val repository: SplashDataContract.Repository
) : ViewModel() {

    val isConnected: MutableLiveData<Boolean> by lazy {
        repository.connection
    }

    fun checkNetworkStatus(context: Context) {
        repository.checkConnection(context)
    }

}