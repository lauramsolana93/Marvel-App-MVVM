package com.kotlin.trifork.marvelapp.model.info

import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import com.kotlin.trifork.marvelapp.common.data.local.entity.SerieEntity
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiComicWrapper
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerieWrapper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface InfoDataContract {

    interface Repository {
        val comicInfo: MutableLiveData<ComicWrapper>
        val serieInfo: MutableLiveData<SerieWrapper>
        val errorDto: MutableLiveData<ErrorDto>
        val comicInfoDB: MutableLiveData<ComicDB>
        val serieInfoDB: MutableLiveData<SerieDB>
        val complete: MutableLiveData<Boolean>

        fun getSerieById(id: Int): Disposable
        fun getComicById(id: Int): Disposable
        fun addComicInfoToDB(comicDb: ComicDB): Disposable
        fun addSerieInfoToDB(serieDb: SerieDB): Disposable
    }

    interface Remote {
        fun getSerieById(id: Int): Single<ApiSerieWrapper>
        fun getComicById(id: Int): Single<ApiComicWrapper>
    }

    interface Local {
        fun addComicInfoToDB(comicDb: ComicDB): Completable
        fun addSerieInfoToDB(serieDb: SerieDB): Completable

    }
}