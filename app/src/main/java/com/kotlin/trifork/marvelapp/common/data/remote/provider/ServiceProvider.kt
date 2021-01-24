package com.kotlin.trifork.marvelapp.common.data.remote.provider

import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiCharacterWrapperResponse
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiComicWrapper
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerieWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceProvider {

    @GET("characters")
    fun getCharacters(): Single<ApiCharacterWrapperResponse>

    @GET("characters/{characterId}/comics")
    fun getComicsByCharacterId(@Path("characterId") id: Int): Single<ApiComicWrapper>

    @GET("characters/{characterId}/series")
    fun getSeriesByCharacterId(@Path("characterId") id: Int): Single<ApiSerieWrapper>

    @GET("comics/{comicId}")
    fun getComicById(@Path("comicId") id: Int): Single<ApiComicWrapper>

    @GET("series/{seriesId}")
    fun getSerieById(@Path("seriesId") id: Int): Single<ApiSerieWrapper>


}