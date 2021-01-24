package com.kotlin.trifork.marvelapp.common.utils.mapper

import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.dto.SerieDB
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import com.kotlin.trifork.marvelapp.common.data.local.entity.SerieEntity

fun ComicEntity.mapToComicDb() = ComicDB(
    id = id,
    title = title,
    description = description,
    thumbnail = thumbnails
)

fun SerieEntity.mapToSerieDB() = SerieDB(
    id = id,
    title = title,
    description = description,
    thumbnail = thumbnails
)

fun List<SerieEntity>.mapToListOfSerieDB(): List<SerieDB> {

    val listSeriesDB = ArrayList<SerieDB>()
    this.forEach {
        listSeriesDB.add(it.mapToSerieDB())
    }

    return listSeriesDB
}

fun List<ComicEntity>.mapToListOfComicDB(): List<ComicDB> {

    val listSComicDB = ArrayList<ComicDB>()
    this.forEach {
        listSComicDB.add(it.mapToComicDb())
    }

    return listSComicDB
}
