package com.yggdralisk.booksstorage.ui.main.provider

import com.yggdralisk.booksstorage.model.BookModel

interface BooksProvider {
    fun getBooks(): ArrayList<BookModel>

    fun getDataSize(): Int

    fun getBook(index: Int): BookModel?
}