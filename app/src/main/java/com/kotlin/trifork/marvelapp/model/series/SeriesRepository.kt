package com.kotlin.trifork.marvelapp.model.series

import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.data.dto.SerieDB
import com.kotlin.trifork.marvelapp.common.data.dto.SerieWrapper
import com.kotlin.trifork.marvelapp.common.utils.mapper.mapToListOfSerieDB
import com.kotlin.trifork.marvelapp.common.utils.mapper.mapToSeriesWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SeriesRepository(
    private val remote: SeriesDataContract.Remote,
    private val local: SeriesDataContract.Local
) : SeriesDataContract.Repository {

    override val serie: MutableLiveData<SerieWrapper> by lazy {
        MutableLiveData<SerieWrapper>()
    }
    override val errorDto: MutableLiveData<ErrorDto> by lazy {
        MutableLiveData<ErrorDto>()
    }
    override val serieDB: MutableLiveData<List<SerieDB>> by lazy {
        MutableLiveData<List<SerieDB>>()
    }


    override fun getSeriesByCharacterId(id: Int): Disposable {
        return remote.getSeriesByCharacterId(id)
            .map { it.mapToSeriesWrapper() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> serie.value = data },
                { error -> errorDto.value = ErrorDto(error.message) }
            )
    }

    override fun getSeriesFromDB(): Disposable {
        return local.getSeriesFromDB()
            .map { it.mapToListOfSerieDB() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> serieDB.value = data },
                { error -> errorDto.value = ErrorDto(error.message) }
            )
    }


}