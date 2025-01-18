package com.plcoding.bookpedia.data.repository

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import com.plcoding.bookpedia.data.mappers.toBook
import com.plcoding.bookpedia.data.network.KtorRemoteBookDataSource

class DefaultBookRepository(
    private val dataSource: KtorRemoteBookDataSource
) {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return dataSource
            .searchBooks(query)
            .map { dto ->
                dto.books.map { it.toBook() }
            }
    }
}