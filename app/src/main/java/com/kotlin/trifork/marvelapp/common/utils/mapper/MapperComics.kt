package com.kotlin.trifork.marvelapp.common.utils.mapper

import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.common.data.remote.datamodel.*

fun ApiComicWrapper.maToComicWrapper() = ComicWrapper(
    code = code,
    status = status,
    copyright = copyright,
    attributionText = attributionText,
    attributionHTML = attributionHTML,
    data = data?.mapToComicContainer(),
    etag = etag
)

private fun ApiComicContainer.mapToComicContainer(): ComicContainer {
    val results = ArrayList<Comic>()

    this.results.forEach {
        results.add(it.mapToComic())
    }

    return ComicContainer(
        offset = offset,
        limit = limit,
        total = total,
        count = count,
        results = results
    )
}

private fun ApiComic.mapToComic(): Comic {
    val urls = ArrayList<UrlSummary>()
    val variants = ArrayList<Summary>()
    val collections = ArrayList<Summary>()
    val collectedIssues = ArrayList<Summary>()
    val images = ArrayList<Image>()
    val textObjects = ArrayList<TextObjects>()
    val dates = ArrayList<ComicDate>()
    val prices = ArrayList<ComicPrice>()

    this.urls?.forEach {
        urls.add(it.mapToUrlSummary())
    }

    this.variants?.forEach {
        variants.add(it.mapToSummary())
    }

    this.collections?.forEach {
        collections.add(it.mapToSummary())
    }

    this.collectedIssues?.forEach {
        collectedIssues.add(it.mapToSummary())
    }

    this.images?.forEach {
        images.add(it.mapToImage())
    }

    this.textObjects?.forEach {
        textObjects.add(it.mapToTextObjects())
    }

    this.dates?.forEach {
        dates.add(it.mapToComicDate())
    }

    this.prices?.forEach {
        prices.add(it.mapToComicPrice())
    }

    return Comic(
        id = id,
        digitalId = digitalId,
        title = title,
        issueNumber = issueNumber,
        variantDescription = variantDescription,
        description = description,
        modified = modified,
        isbn = isbn,
        upc = upc,
        diamondCode = diamondCode,
        ean = ean,
        issn = issn,
        format = format,
        pageCount = pageCount,
        textObjects = textObjects,
        resourceURI = resourceURI,
        urls = urls,
        series = series?.mapToSummary(),
        variants = variants,
        collections = collections,
        collectedIssues = collectedIssues,
        dates = dates,
        prices = prices,
        thumbnail = thumbnail?.mapToImage(),
        images = images,
        creators = creators?.mapToCreatorList(),
        characters = characters?.mapToCharacterList(),
        stories = stories?.mapToStoryList(),
        events = events?.mapToEventsList()


    )
}

private fun ApiTextObjects.mapToTextObjects() = TextObjects(
    type = type,
    language = language,
    text = text

)

private fun ApiComicDate.mapToComicDate() = ComicDate(
    type = type,
    date = date
)

private fun ApiComicPrice.mapToComicPrice() = ComicPrice(
    type = type,
    price = price
)

fun ApiCreatorList.mapToCreatorList(): CreatorList {
    var items = ArrayList<CreatorSummary>()

    this.items?.forEach {
        items.add(it.mapToCreatorSummary())
    }

    return CreatorList(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}

private fun ApiCreatorSummary.mapToCreatorSummary() = CreatorSummary(
    resourceURI = resourceURI,
    name = name,
    role = role
)

fun ApiCharactersList.mapToCharacterList(): CharactersList {
    var items = ArrayList<CharacterSummary>()
    this.items?.forEach {
        items.add(it.mapToCharacterSummary())
    }

    return CharactersList(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}

private fun ApiCharacterSummary.mapToCharacterSummary() = CharacterSummary(
    resourceURI = resourceURI,
    name = name,
    role = role
)

private fun ApiStoryList.mapToStoryList(): StoryList {
    var items = ArrayList<StorySummary>()
    this.items?.forEach {
        items.add(it.mapToStorySummary())
    }

    return StoryList(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}

private fun ApiStorySummary.mapToStorySummary() = StorySummary(
    resourceURI = resourceURI,
    name = name,
    type = type
)

private fun ApiEventsList.mapToEventsList(): EventsList {
    var items = ArrayList<EventsSummary>()
    this.items?.forEach {
        items.add(it.mapToEventsSummary())
    }

    return EventsList(
        available = available,
        returned = returned,
        collectionURI = collectionURI,
        items = items
    )
}

fun ApiEventsSummary.mapToEventsSummary() = EventsSummary(
    resourceURI = resourceURI,
    name = name
)





