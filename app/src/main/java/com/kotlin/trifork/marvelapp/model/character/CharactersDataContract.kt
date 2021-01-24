package com.kotlin.trifork.marvelapp.model.character

import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.CharacterWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiCharacterWrapperResponse
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface CharactersDataContract {

    interface Repository {
        val characters: MutableLiveData<CharacterWrapper>
        val errorDto: MutableLiveData<ErrorDto>
        fun getCharacters(): Disposable
    }

    interface Remote {
        fun getCharacters(): Single<ApiCharacterWrapperResponse>
    }
}