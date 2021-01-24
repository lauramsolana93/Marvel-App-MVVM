package com.kotlin.trifork.marvelapp.model.info

import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.dto.SerieDB
import com.kotlin.trifork.marvelapp.common.data.local.DataBase
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import com.kotlin.trifork.marvelapp.common.data.local.entity.SerieEntity
import io.reactivex.Completable

class InfoLocal constructor(
    private val dataBase: DataBase
) : InfoDataContract.Local {


    override fun addComicInfoToDB(comicDb: ComicDB): Completable {
        return Completable.fromAction {
            dataBase.favouritesDao().addComic(
                ComicEntity(
                    comicDb.id,
                    comicDb.title,
                    comicDb.description,
                    comicDb.thumbnail ?: byteArrayOf()
                )
            )
        }


    }

    override fun addSerieInfoToDB(serieDb: SerieDB): Completable {
        return Completable.fromAction {
            dataBase.favouritesDao().addSerie(
                SerieEntity(
                    serieDb.id,
                    serieDb.title,
                    serieDb.description,
                    serieDb.thumbnail ?: byteArrayOf()
                )
            )
        }
    }


}