package com.yggdralisk.booksstorage.ui.add

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.stream.JsonReader
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.model.InputRow
import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber
import java.io.StringReader

class AddBookViewModel : ViewModel() {
    private lateinit var inputRows: MutableLiveData<ArrayList<InputRow>>
    private lateinit var realmBooks: RealmResults<InputRow>

    fun getInputRows(resourcesContext: Application): MutableLiveData<ArrayList<InputRow>> {
        if (!::inputRows.isInitialized) {
            inputRows = MutableLiveData()
            inputRows.value = getDefaultRows(resourcesContext)
            loadBooks()
        }

        return inputRows
    }

    private fun getDefaultRows(resourcesContext: Context): ArrayList<InputRow> {
        val typedArray = resourcesContext.resources.obtainTypedArray(R.array.initInputRows)
        val arr: ArrayList<InputRow> = arrayListOf()
        for (i in 0 until typedArray.length()) {
            try {
                arr.add(InputRow.fromJson(typedArray.getString(i)))
            } catch (e: JsonParseException) {
                Timber.e("Error at converting typed array of initial rows to object with GSON")
                Timber.e(e)
            }
        }

        typedArray.recycle()
        return arr
    }

    private fun loadBooks() {
        if (!::realmBooks.isInitialized) {
            val realm = Realm.getDefaultInstance()

            realmBooks = realm.where(InputRow::class.java).findAll()

            realmBooks.addChangeListener { results, changeSet ->
                Timber.d("New database inputRows results. Size: ${results.size}")
                inputRows.value?.removeAll(results.slice(changeSet.deletions.toList()))
                inputRows.value?.addAll(results.slice(changeSet.insertions.toList()))
            }
        }
    }
}