package com.yggdralisk.booksstorage.model

import io.realm.RealmObject

open class BookModel(
        open var title: String = ""
) : RealmObject()