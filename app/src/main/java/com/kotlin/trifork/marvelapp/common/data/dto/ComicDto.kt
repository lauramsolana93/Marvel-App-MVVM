package com.kotlin.trifork.marvelapp.common.data.dto

data class ComicWrapper(
    var code : Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data : ComicContainer?,
    var etag: String?

)

data class ComicContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: ArrayList<Comic>?
)

data class Comic(
    var id: Int?,
    var digitalId: Int?,
    var title: String?,
    var issueNumber: Double?,
    var variantDescription: String?,
    var description: String?,
    var modified: String?,
    var isbn: String?,
    var upc: String?,
    var diamondCode: String?,
    var ean: String?,
    var issn: String?,
    var format: String?,
    var pageCount: Int?,
    var textObjects: ArrayList<TextObjects>?,
    var resourceURI: String?,
    var urls: ArrayList<UrlSummary>?,
    var series: Summary?,
    var variants: ArrayList<Summary>?,
    var collections: ArrayList<Summary>?,
    var collectedIssues: ArrayList<Summary>?,
    var dates: ArrayList<ComicDate>?,
    var prices: ArrayList<ComicPrice>?,
    var thumbnail: Image?,
    var images: ArrayList<Image>?,
    var creators: CreatorList?,
    var characters: CharactersList?,
    var stories: StoryList?,
    var events: EventsList?


)
data class TextObjects(
    var type: String?,
    var language: String?,
    var text: String?
)

data class ComicDate(
    var type: String?,
    var date: String?
)

data class ComicPrice(
    var type: String?,
    var price: Float?
)

data class CreatorList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<CreatorSummary>?
)

data class CreatorSummary(
    var resourceURI: String?,
    var name: String?,
    var role: String?
)

data class CharactersList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<CharacterSummary>?
)

data class CharacterSummary(
    var resourceURI: String?,
    var name: String?,
    var role: String?
)

data class StoryList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<StorySummary>?
)

data class StorySummary(
    var resourceURI: String?,
    var name: String?,
    var type: String?
)

data class EventsList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<EventsSummary>?
)

data class EventsSummary(
    var resourceURI: String?,
    var name: String?
)