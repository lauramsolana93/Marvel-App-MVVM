package com.kotlin.trifork.marvelapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.data.dto.SerieDB
import com.kotlin.trifork.marvelapp.common.data.dto.SerieWrapper
import com.kotlin.trifork.marvelapp.model.series.SeriesDataContract

class SeriesViewModel constructor(
    private val repository: SeriesDataContract.Repository
) : ViewModel() {

    val serie: MutableLiveData<SerieWrapper> by lazy {
        repository.serie
    }

    val error: MutableLiveData<ErrorDto> by lazy {
        repository.errorDto
    }

    val serieDB: MutableLiveData<List<SerieDB>> by lazy {
        repository.serieDB
    }

    fun getSerieByCharacterId(id: Int) {
        repository.getSeriesByCharacterId(id)
    }

    fun getSeriesFromDB() {
        repository.getSeriesFromDB()
    }
}