package com.kotlin.trifork.marvelapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.trifork.marvelapp.common.data.dto.CharacterWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.model.character.CharactersDataContract

class CharactersViewModel constructor(
    private val repository: CharactersDataContract.Repository
) : ViewModel() {

    val characters: MutableLiveData<CharacterWrapper> by lazy {
        repository.characters
    }

    val error: MutableLiveData<ErrorDto> by lazy {
        repository.errorDto
    }

    fun getCharacters() {
        repository.getCharacters()
    }
}