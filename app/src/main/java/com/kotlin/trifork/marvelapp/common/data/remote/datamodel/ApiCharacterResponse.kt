package com.kotlin.trifork.marvelapp.common.data.remote.datamodel

import java.util.*

data class ApiCharacterWrapperResponse(
    var code: Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data: ApiCharacterContainer?,
    var etag: String?

)

data class ApiCharacterContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: ArrayList<ApiCharacter>?
)

data class ApiCharacter(
    var id: Int?,
    var name: String?,
    var description: String?,
    var modified: Date?,
    var resourceURI: String?,
    var urls: ArrayList<ApiUrlSummary>?,
    var thumbnail: ApiImage?,
    var comics: ApiComicSummary?,
    var stories: ApiStorieSummary?,
    var events: ApiEventSummary?,
    var series: ApiSerieSummary?

)

data class ApiImage(
    var path: String?,
    var extension: String?
)

data class ApiUrlSummary(
    var type: String?,
    var url: String?
)

data class ApiComicSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiSummary>?
)

data class ApiStorieSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiSummary>?
)

data class ApiEventSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiSummary>?
)

data class ApiSerieSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiSummary>?
)

data class ApiSummary(
    var resourceURI: String?,
    var name: String?
)