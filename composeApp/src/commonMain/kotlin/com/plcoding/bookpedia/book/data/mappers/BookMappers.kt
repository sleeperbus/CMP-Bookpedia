package com.plcoding.bookpedia.book.data.mappers

import com.plcoding.bookpedia.book.data.database.BookEntity
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.data.dto.SearchedBookDto

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = languages ?: emptyList(),
        firstPublishYear = firstPublishYear?.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions ?: 0
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishedYear = firstPublishYear,
        ratingAverage = averageRating,
        ratingCount = ratingCount,
        numPagesMedian = numPages,
        numEditions = numEditions
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = "",
        imageUrl = imageUrl,
        authors = authors,
        description = description,
        languages = languages,
        firstPublishYear = firstPublishedYear,
        averageRating = ratingAverage,
        ratingCount = ratingCount,
        numPages = numPagesMedian,
        numEditions = numEditions
    )
}