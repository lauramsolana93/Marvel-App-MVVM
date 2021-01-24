package com.kotlin.trifork.marvelapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.model.info.InfoDataContract

class InfoViewModel constructor(
    private val repository: InfoDataContract.Repository
) : ViewModel() {

    val comicInfo: MutableLiveData<ComicWrapper> by lazy {
        repository.comicInfo
    }

    val serieInfo: MutableLiveData<SerieWrapper> by lazy {
        repository.serieInfo
    }

    val errorDto: MutableLiveData<ErrorDto> by lazy {
        repository.errorDto
    }

    val complete: MutableLiveData<Boolean> by lazy {
        repository.complete
    }

    fun getSerieById(id: Int) {
        repository.getSerieById(id)
    }

    fun getComicById(id: Int) {
        repository.getComicById(id)
    }

    fun addSerieInfoToDb(serieDb: SerieDB){
        repository.addSerieInfoToDB(serieDb)
    }

    fun addComicInfoToDb(comicDb: ComicDB){
        repository.addComicInfoToDB(comicDb)
    }


}