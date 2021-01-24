package com.kotlin.trifork.marvelapp

import com.kotlin.trifork.marvelapp.common.test.*
import com.kotlin.trifork.marvelapp.common.test.ComicDummyData.Companion.id
import com.kotlin.trifork.marvelapp.model.comics.ComicsDataContract
import com.kotlin.trifork.marvelapp.model.comics.ComicsRepository
import com.kotlin.trifork.marvelapp.model.info.InfoDataContract
import com.kotlin.trifork.marvelapp.model.info.InfoRepository
import com.kotlin.trifork.marvelapp.model.series.SeriesDataContract
import com.kotlin.trifork.marvelapp.model.series.SeriesRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class DatabaseTest {

    private var localComic = mockk<ComicsDataContract.Local>()
    private var remoteComic = mockk<ComicsDataContract.Remote>()
    private lateinit var repositoryComic: ComicsRepository
    private var localSerie = mockk<SeriesDataContract.Local>()
    private var remoteSerie = mockk<SeriesDataContract.Remote>()
    private lateinit var repositorySerie: SeriesRepository
    private var localInfo = mockk<InfoDataContract.Local>()
    private var remoteInfo = mockk<InfoDataContract.Remote>()
    private lateinit var repositoryInfo: InfoRepository
    private val listComics = ComicDummyList.comicsListItem(
        starterId = 1, listSize = 3
    )
    private val listSeries = SerieDummyList.serieListItem(
        starterId = 1, listSize = 3
    )
    private val comic = ComicDummyDB.comicDB()
    private val serie = SerieDummyDB.serieDB()

    @Before
    fun init(){
        repositoryComic = ComicsRepository(remoteComic, localComic)
        repositorySerie = SeriesRepository(remoteSerie, localSerie)
        repositoryInfo = InfoRepository(remoteInfo, localInfo)
    }

    @Test
    fun getComicsFromDB() {
        every { localComic.getComicsFromDB() } returns Single.just(listComics)
    }

    @Test
    fun getSeriesFromDB(){
        every { localSerie.getSeriesFromDB() } returns Single.just(listSeries)
    }

    @Test
    fun addComicToDB(){
        every { localInfo.addComicInfoToDB(comic) } returns Completable.complete()
    }

    @Test
    fun addSerieToDB(){
        every { localInfo.addSerieInfoToDB(serie) } returns Completable.complete()
    }


}