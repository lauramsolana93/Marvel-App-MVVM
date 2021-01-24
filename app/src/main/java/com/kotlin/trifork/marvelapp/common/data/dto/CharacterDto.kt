package com.kotlin.trifork.marvelapp.common.data.dto

import java.util.*

data class CharacterWrapper(
    var code: Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data: CharacterContainer?,
    var etag: String?

)

data class CharacterContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: ArrayList<CharacterDto>?
)

data class CharacterDto(
    var id: Int?,
    var name: String?,
    var description: String?,
    var modified: Date?,
    var resourceURI: String?,
    var urls: ArrayList<UrlSummary>?,
    var thumbnail: Image?,
    var comics: ComicSummary?,
    var stories: StorieSummary?,
    var events: EventSummary?,
    var series: SeriesSummary?

)

data class Image(
    var path: String?,
    var extension: String?
)

data class UrlSummary(
    var type: String?,
    var url: String?
)


data class ComicSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<Summary>?
)

data class StorieSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<Summary>?
)

data class EventSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<Summary>?
)

data class SeriesSummary(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<Summary>?
)

data class Summary(
    var resourceURI: String?,
    var name: String?
)
