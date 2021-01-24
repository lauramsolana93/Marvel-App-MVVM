package com.kotlin.trifork.marvelapp.common.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.trifork.marvelapp.common.data.local.entity.ComicEntity
import com.kotlin.trifork.marvelapp.common.data.local.entity.SerieEntity
import io.reactivex.Single

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM comic")
    fun getAllComics(): Single<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComic(vararg comic: ComicEntity)

    @Query("SELECT * FROM serie")
    fun getAllSeries(): Single<List<SerieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSerie(vararg serie: SerieEntity)
}