package com.kotlin.trifork.marvelapp.model.character

import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiCharacterWrapperResponse
import com.kotlin.trifork.marvelapp.common.data.remote.provider.ServiceProvider
import io.reactivex.Single

class CharactersRemote(
    private val serviceProvider: ServiceProvider
) : CharactersDataContract.Remote {

    override fun getCharacters(): Single<ApiCharacterWrapperResponse> {
        return serviceProvider.getCharacters()
    }
}