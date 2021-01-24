package com.kotlin.trifork.marvelapp.common.data.remote.datamodel

data class ApiComicWrapper(
    var code: Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data: ApiComicContainer?,
    var etag: String?

)

data class ApiComicContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: ArrayList<ApiComic>
)

data class ApiComic(
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
    var pageCount: Int,
    var textObjects: ArrayList<ApiTextObjects>?,
    var resourceURI: String?,
    var urls: ArrayList<ApiUrlSummary>?,
    var series: ApiSummary?,
    var variants: ArrayList<ApiSummary>?,
    var collections: ArrayList<ApiSummary>?,
    var collectedIssues: ArrayList<ApiSummary>?,
    var dates: ArrayList<ApiComicDate>?,
    var prices: ArrayList<ApiComicPrice>?,
    var thumbnail: ApiImage?,
    var images: ArrayList<ApiImage>?,
    var creators: ApiCreatorList?,
    var characters: ApiCharactersList?,
    var stories: ApiStoryList?,
    var events: ApiEventsList?


)

data class ApiTextObjects(
    var type: String?,
    var language: String?,
    var text: String?
)

data class ApiComicDate(
    var type: String?,
    var date: String?
)

data class ApiComicPrice(
    var type: String?,
    var price: Float?
)

data class ApiCreatorList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiCreatorSummary>?
)

data class ApiCreatorSummary(
    var resourceURI: String?,
    var name: String?,
    var role: String?
)

data class ApiCharactersList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiCharacterSummary>?
)

data class ApiCharacterSummary(
    var resourceURI: String?,
    var name: String?,
    var role: String?
)

data class ApiStoryList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiStorySummary>?
)

data class ApiStorySummary(
    var resourceURI: String?,
    var name: String?,
    var type: String?
)

data class ApiEventsList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ApiEventsSummary>?
)

data class ApiEventsSummary(
    var resourceURI: String?,
    var name: String?
)


