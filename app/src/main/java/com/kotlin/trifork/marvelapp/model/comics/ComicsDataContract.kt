package com.kotlin.trifork.marvelapp.model.comics

import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.dto.ComicWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiComicWrapper
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface ComicsDataContract {

    interface Repository {
        val comic: MutableLiveData<ComicWrapper>
        val errorDto: MutableLiveData<ErrorDto>
        val comicDB: MutableLiveData<List<ComicDB>>
        fun getComicsByCharacterId(id: Int): Disposable
        fun getComicsFromDB(): Disposable

    }

    interface Remote {
        fun getComicsByCharacterId(id: Int): Single<ApiComicWrapper>
    }

    interface Local {
        fun getComicsFromDB(): Single<List<ComicEntity>>
    }
}