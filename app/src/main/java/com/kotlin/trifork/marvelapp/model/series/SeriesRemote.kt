package com.kotlin.trifork.marvelapp.model.series

import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerieWrapper
import com.kotlin.trifork.marvelapp.common.data.remote.provider.ServiceProvider
import io.reactivex.Single

class SeriesRemote(
    private val serviceProvider: ServiceProvider
) : SeriesDataContract.Remote {

    override fun getSeriesByCharacterId(id: Int): Single<ApiSerieWrapper> {
        return serviceProvider.getSeriesByCharacterId(id)
    }

}