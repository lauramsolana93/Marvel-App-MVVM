package com.kotlin.trifork.marvelapp.common.test

import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.dto.SerieDB
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import com.kotlin.trifork.marvelapp.common.data.local.entity.SerieEntity

object SerieDummyList : ComicDummyData(){


    fun serieListItem(starterId: Int, listSize: Int): List<SerieEntity> {
        val list = mutableListOf<SerieEntity>()
        val end = starterId + listSize
        for(i in starterId until end) {
            val item = SerieEntity(
                id = i,
                title = title,
                description = description,
                thumbnails = thumbnails
            )
            list.add(item)
        }
        return list.toList()
    }

}

object SerieDummyDB : SerieDummyData(){
    fun serieDB() = SerieDB(
        id = ComicDummyData.id,
        title = ComicDummyData.title,
        description = ComicDummyData.description,
        thumbnail = ComicDummyData.thumbnails
    )
}