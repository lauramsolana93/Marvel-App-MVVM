package com.kotlin.trifork.marvelapp.common.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSummary

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "thumbnails") var thumbnails: ByteArray
)