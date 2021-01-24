package com.kotlin.trifork.marvelapp.model.comics

import com.kotlin.trifork.marvelapp.common.data.local.DataBase
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import io.reactivex.Single

class ComicLocal constructor(
    private val database: DataBase
) : ComicsDataContract.Local {

    override fun getComicsFromDB(): Single<List<ComicEntity>> {
        return database.favouritesDao().getAllComics()
    }
}