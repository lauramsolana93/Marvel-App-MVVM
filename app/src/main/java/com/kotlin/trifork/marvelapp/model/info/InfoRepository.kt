package com.kotlin.trifork.marvelapp.model.info

import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.common.utils.mapper.maToComicWrapper
import com.kotlin.trifork.marvelapp.common.utils.mapper.mapToComicDb
import com.kotlin.trifork.marvelapp.common.utils.mapper.mapToSerieDB
import com.kotlin.trifork.marvelapp.common.utils.mapper.mapToSeriesWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class InfoRepository (
    private val remote: InfoDataContract.Remote,
    private val local: InfoDataContract.Local
) : InfoDataContract.Repository {

    override val comicInfo: MutableLiveData<ComicWrapper> by lazy {
        MutableLiveData<ComicWrapper>()
    }

    override val serieInfo: MutableLiveData<SerieWrapper> by lazy {
        MutableLiveData<SerieWrapper>()
    }

    override val errorDto: MutableLiveData<ErrorDto> by lazy {
        MutableLiveData<ErrorDto>()
    }
    override val comicInfoDB: MutableLiveData<ComicDB> by lazy {
        MutableLiveData<ComicDB>()
    }

    override val serieInfoDB: MutableLiveData<SerieDB> by lazy {
        MutableLiveData<SerieDB>()
    }

    override val complete: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }



    override fun getSerieById(id: Int): Disposable {
        return remote.getSerieById(id)
            .map { it.mapToSeriesWrapper() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data -> serieInfo.value = data},
                {error -> errorDto.value = ErrorDto(error.message) }
            )
    }

    override fun getComicById(id: Int): Disposable {
        return remote.getComicById(id)
            .map { it.maToComicWrapper() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data -> comicInfo.value = data},
                {error -> errorDto.value = ErrorDto(error.message) }
            )
    }


    override fun addComicInfoToDB(comicDb: ComicDB): Disposable {
        return local.addComicInfoToDB(comicDb)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete {throwable: Throwable ->
                errorDto.value = ErrorDto(throwable.message)
                complete.value = false
                true
            }
            .doOnComplete {
                complete.value = true
            }
            .subscribe()
    }

    override fun addSerieInfoToDB(serieDB: SerieDB): Disposable {
        return local.addSerieInfoToDB(serieDB)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete {throwable: Throwable ->
                errorDto.value = ErrorDto(throwable.message)
                true
            }
            .doOnComplete {
                complete.value = true
            }
            .subscribe()
    }


}