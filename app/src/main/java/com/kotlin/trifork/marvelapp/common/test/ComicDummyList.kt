package com.kotlin.trifork.marvelapp.common.test

import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity

object ComicDummyList : ComicDummyData(){


    fun comicsListItem(starterId: Int, listSize: Int): List<ComicEntity> {
        val list = mutableListOf<ComicEntity>()
        val end = starterId + listSize
        for(i in starterId until end) {
            val item = ComicEntity(
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

object ComicDummyDB: ComicDummyData() {

    fun comicDB() = ComicDB(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnails
    )
}