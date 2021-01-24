package com.kotlin.trifork.marvelapp.model.comics

import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiComicWrapper
import com.kotlin.trifork.marvelapp.common.data.remote.provider.ServiceProvider
import io.reactivex.Single

class ComicsRemote(
    private val serviceProvider: ServiceProvider
) : ComicsDataContract.Remote {

    override fun getComicsByCharacterId(id: Int): Single<ApiComicWrapper> {
        return serviceProvider.getComicsByCharacterId(id)
    }
}