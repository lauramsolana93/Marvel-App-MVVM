package com.kotlin.trifork.marvelapp.common.data.remote.datamodel

import com.kotlin.trifork.marvelapp.common.data.dto.*

data class ApiSerieWrapper(
    var code : Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data : ApiSerieContainer?,
    var etag: String?

)

data class ApiSerieContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: ArrayList<ApiSerie>?
)

data class ApiSerie(
    var id: Int?,
    var title: String?,
    var description: String?,
    var resourceURI: String?,
    var urls: ArrayList<ApiUrlSummary>?,
    var startYear: Int?,
    var endYear: Int?,
    var rating: String?,
    var modified: String?,
    var thumbnail: ApiImage?,
    var comics: ApiComicSummary?,
    var stories: ApiStorySummary?,
    var events: ApiEventsSummary?,
    var characters: ApiCharactersList?,
    var creators: ApiCreatorList?,
    var next: ApiSummary?,
    var previous: ApiSummary?
)