package com.kotlin.trifork.marvelapp.model.splash

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.utils.helper.ConnectivityHelper

class SplashRepository : SplashDataContract.Repository {

    private var connectivtyHelpper = ConnectivityHelper()

    override val connection: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    override fun checkConnection(context: Context) {
        connection.postValue(connectivtyHelpper.isConnectedToNetwork(context))
    }

}