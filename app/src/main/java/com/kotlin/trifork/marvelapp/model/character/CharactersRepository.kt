package com.kotlin.trifork.marvelapp.model.character

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.CharacterWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.utils.mapper.mapToCharacterWarapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CharactersRepository(private val remote: CharactersDataContract.Remote) :
        CharactersDataContract.Repository {

    override val characters: MutableLiveData<CharacterWrapper> by lazy {
        MutableLiveData<CharacterWrapper>()
    }

    override val errorDto: MutableLiveData<ErrorDto> by lazy {
        MutableLiveData<ErrorDto>()
    }


    override fun getCharacters(): Disposable {
        return remote.getCharacters()
            .map { it.mapToCharacterWarapper()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data -> characters.value = data},
                { error -> errorDto.value = ErrorDto(error.message) }
            )
    }


}