package com.yggdralisk.booksstorage.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yggdralisk.booksstorage.model.BookModel
import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber

class MainViewModel : ViewModel() {
    private lateinit var books: MutableLiveData<ArrayList<BookModel>>
    private lateinit var realmBooks: RealmResults<BookModel>

    fun getBooks(): MutableLiveData<ArrayList<BookModel>> {
        if (!::books.isInitialized) {
            books = MutableLiveData()
            books.value = arrayListOf()
            loadBooks()
        }

        return books
    }

    private fun loadBooks() {
        if (!::realmBooks.isInitialized) {
            val realm = Realm.getDefaultInstance()

            realmBooks = realm.where(BookModel::class.java).findAll()

            realmBooks.addChangeListener { results, changeSet ->
                Timber.d("New database books results. Size: ${results.size}")
                books.value?.removeAll(results.slice(changeSet.deletions.toList()))
                books.value?.addAll(results.slice(changeSet.insertions.toList()))
            }
        }
    }
}
