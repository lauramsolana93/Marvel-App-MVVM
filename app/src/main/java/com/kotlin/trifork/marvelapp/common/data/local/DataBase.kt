package com.kotlin.trifork.marvelapp.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.trifork.marvelapp.common.data.local.dao.FavouritesDao
import com.kotlin.trifork.marvelapp.common.data.local.entity.*

@Database(
    entities = [
        ComicEntity::class,
        SerieEntity::class],
    version = 1,
    exportSchema = false
)

abstract class DataBase : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}