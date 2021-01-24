package com.kotlin.trifork.marvelapp.model.series

import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.data.dto.SerieDB
import com.kotlin.trifork.marvelapp.common.data.dto.SerieWrapper
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import com.kotlin.trifork.marvelapp.common.data.local.entity.SerieEntity
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerieWrapper
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import org.w3c.dom.Entity

interface SeriesDataContract {

    interface Repository {
        val serie: MutableLiveData<SerieWrapper>
        val errorDto: MutableLiveData<ErrorDto>
        val serieDB: MutableLiveData<List<SerieDB>>
        fun getSeriesByCharacterId(id: Int): Disposable
        fun getSeriesFromDB(): Disposable
    }

    interface Remote {
        fun getSeriesByCharacterId(id: Int): Single<ApiSerieWrapper>
    }

    interface Local {
        fun getSeriesFromDB(): Single<List<SerieEntity>>
    }
}