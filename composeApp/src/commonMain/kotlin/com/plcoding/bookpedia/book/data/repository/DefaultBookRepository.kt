package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import com.plcoding.bookpedia.book.data.mappers.toBook
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.domain.BookRepository

class DefaultBookRepository(
    private val dataSource: KtorRemoteBookDataSource
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return dataSource
            .searchBooks(query)
            .map { dto ->
                dto.books.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return dataSource.getBookDetails(bookId)
            .map { it.description }
    }
}