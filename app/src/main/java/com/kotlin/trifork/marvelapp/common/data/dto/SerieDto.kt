package com.kotlin.trifork.marvelapp.common.data.dto

data class SerieWrapper(
    var code: Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data: SerieContainer?,
    var etag: String?

)

data class SerieContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: ArrayList<Serie>?
)

data class Serie(
    var id: Int?,
    var title: String?,
    var description: String?,
    var resourceURI: String?,
    var urls: ArrayList<UrlSummary>?,
    var startYear: Int?,
    var endYear: Int?,
    var rating: String?,
    var modified: String?,
    var thumbnail: Image?,
    var comics: ComicSummary?,
    var stories: StorySummary?,
    var events: EventsSummary?,
    var characters: CharactersList?,
    var creators: CreatorList?,
    var next: Summary?,
    var previous: Summary?
)