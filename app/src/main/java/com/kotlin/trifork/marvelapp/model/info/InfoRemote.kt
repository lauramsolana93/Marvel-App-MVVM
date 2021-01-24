package com.kotlin.trifork.marvelapp.model.info

import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiComicWrapper
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerieWrapper
import com.kotlin.trifork.marvelapp.common.data.remote.provider.ServiceProvider
import io.reactivex.Single

class InfoRemote(
    private val serviceProvider: ServiceProvider
) : InfoDataContract.Remote {

    override fun getSerieById(id: Int): Single<ApiSerieWrapper> {
        return serviceProvider.getSerieById(id)
    }

    override fun getComicById(id: Int): Single<ApiComicWrapper> {
        return serviceProvider.getComicById(id)
    }


}