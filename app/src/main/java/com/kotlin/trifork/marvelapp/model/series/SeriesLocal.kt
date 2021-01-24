package com.kotlin.trifork.marvelapp.model.series

import com.kotlin.trifork.marvelapp.common.data.local.DataBase
import com.kotlin.trifork.marvelapp.common.data.local.entity.SerieEntity
import io.reactivex.Single

class SeriesLocal constructor(
    private val dataBase: DataBase
): SeriesDataContract.Local {

    override fun getSeriesFromDB(): Single<List<SerieEntity>> {
        return dataBase.favouritesDao().getAllSeries()
    }


}