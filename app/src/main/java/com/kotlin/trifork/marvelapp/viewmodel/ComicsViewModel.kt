package com.kotlin.trifork.marvelapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.dto.ComicWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.model.comics.ComicsDataContract

class ComicsViewModel constructor(
    private val repository: ComicsDataContract.Repository
) : ViewModel() {

    val comic: MutableLiveData<ComicWrapper> by lazy {
        repository.comic
    }

    val comicDB: MutableLiveData<List<ComicDB>> by lazy {
        repository.comicDB
    }

    val error: MutableLiveData<ErrorDto> by lazy {
        repository.errorDto
    }

    fun getComicsByCharacterId(id: Int) {
        repository.getComicsByCharacterId(id)
    }

    fun getComicsFromDb() {
        repository.getComicsFromDB()
    }
}