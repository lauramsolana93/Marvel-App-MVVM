package com.kotlin.trifork.marvelapp.common.utils.mapper

import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerie
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerieContainer
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiSerieWrapper
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.ApiStorySummary

fun ApiSerieWrapper.mapToSeriesWrapper() = SerieWrapper(
    code = code,
    status = status,
    copyright = copyright,
    attributionText = attributionText,
    attributionHTML = attributionHTML,
    data = data?.mapToSeriesContainer(),
    etag = etag
)

private fun ApiSerieContainer.mapToSeriesContainer(): SerieContainer {
    val results = ArrayList<Serie>()

    this.results?.forEach {
        results.add(it.mapToSerie())
    }

    return SerieContainer(
        offset = offset,
        limit = limit,
        total = total,
        count = count,
        results = results
    )
}

private fun ApiSerie.mapToSerie(): Serie {
    var urls = ArrayList<UrlSummary>()

    this.urls?.forEach {
        urls.add(it.mapToUrlSummary())
    }

    return Serie(
        id = id,
        title = title,
        description = description,
        resourceURI = resourceURI,
        urls = urls,
        startYear = startYear,
        endYear = endYear,
        rating = rating,
        modified = modified,
        thumbnail = thumbnail?.mapToImage(),
        comics = comics?.mapToComicSummary(),
        stories = stories?.mapToStorySummary(),
        events = events?.mapToEventsSummary(),
        characters = characters?.mapToCharacterList(),
        creators = creators?.mapToCreatorList(),
        next = next?.mapToSummary(),
        previous = previous?.mapToSummary()

    )
}

private fun ApiStorySummary.mapToStorySummary() = StorySummary(
    resourceURI = resourceURI,
    name = name,
    type = type
)