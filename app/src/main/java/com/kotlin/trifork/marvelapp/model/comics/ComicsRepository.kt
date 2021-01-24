package com.kotlin.trifork.marvelapp.model.comics

import androidx.lifecycle.MutableLiveData
import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.dto.ComicWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.utils.mapper.maToComicWrapper
import com.kotlin.trifork.marvelapp.common.utils.mapper.mapToListOfComicDB
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ComicsRepository(
    private val remote: ComicsDataContract.Remote,
    private val local: ComicsDataContract.Local
) : ComicsDataContract.Repository {

    override val comic: MutableLiveData<ComicWrapper> by lazy {
        MutableLiveData<ComicWrapper>()
    }

    override val errorDto: MutableLiveData<ErrorDto> by lazy {
        MutableLiveData<ErrorDto>()
    }

    override val comicDB: MutableLiveData<List<ComicDB>> by lazy {
        MutableLiveData<List<ComicDB>>()
    }

    override fun getComicsByCharacterId(id: Int): Disposable {
        return remote.getComicsByCharacterId(id)
            .map { it.maToComicWrapper() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> comic.value = data },
                { error -> errorDto.value = ErrorDto(error.message) }
            )
    }

    override fun getComicsFromDB(): Disposable {
        return local.getComicsFromDB()
            .map { it.mapToListOfComicDB() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> comicDB.value = data },
                { error -> errorDto.value = ErrorDto(error.message) }
            )
    }

}