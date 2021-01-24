package com.kotlin.trifork.marvelapp.common.data.dto

data class SerieDB(
    var id: Int,
    var title: String,
    var description: String,
    var thumbnail: ByteArray?
)