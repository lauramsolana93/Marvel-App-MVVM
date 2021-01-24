package com.kotlin.trifork.marvelapp.common.utils.mapper

import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.*

fun ApiCharacterWrapperResponse.mapToCharacterWarapper() = CharacterWrapper(
    code = code,
    status = status,
    copyright = copyright,
    attributionText = attributionText,
    attributionHTML = attributionHTML,
    data = data?.mapToCharacterContainer(),
    etag = etag
)

private fun ApiCharacterContainer.mapToCharacterContainer(): CharacterContainer {
    val results = ArrayList<CharacterDto>()
    this.results?.forEach {
        results.add(it.mapToCharacter())
    }
    return CharacterContainer(
        offset = offset,
        limit = limit,
        total = total,
        count = count,
        results = results
    )

}

private fun ApiCharacter.mapToCharacter(): CharacterDto {
    var urls = ArrayList<UrlSummary>()

    this.urls?.forEach {
        urls.add(it.mapToUrlSummary())
    }

    return CharacterDto(
        id = id,
        name = name,
        description = description,
        modified = modified,
        resourceURI = resourceURI,
        urls = urls,
        thumbnail = thumbnail?.mapToImage(),
        comics = comics?.mapToComicSummary(),
        stories = stories?.mapToStorieSummary(),
        events = events?.mapToEventSummary(),
        series = series?.mapToSerieSummary()
    )
}

fun ApiImage.mapToImage() = Image(
    path = path,
    extension = extension
)

fun ApiComicSummary.mapToComicSummary(): ComicSummary {
    var items = ArrayList<Summary>()
    this.items?.forEach {
        items.add(it.mapToSummary())
    }
    return ComicSummary(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}


fun ApiStorieSummary.mapToStorieSummary(): StorieSummary {
    var items = ArrayList<Summary>()
    this.items?.forEach {
        items.add(it.mapToSummary())
    }
    return StorieSummary(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}


fun ApiEventSummary.mapToEventSummary(): EventSummary {
    var items = ArrayList<Summary>()
    this.items?.forEach {
        items.add(it.mapToSummary())
    }

    return EventSummary(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}


fun ApiSerieSummary.mapToSerieSummary(): SeriesSummary {
    var items = ArrayList<Summary>()
    this.items?.forEach {
        items.add(it.mapToSummary())
    }

    return SeriesSummary(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}

fun ApiSummary.mapToSummary() = Summary(
    resourceURI = resourceURI,
    name = name
)

fun ApiUrlSummary.mapToUrlSummary() = UrlSummary(
    type = type,
    url = url
)