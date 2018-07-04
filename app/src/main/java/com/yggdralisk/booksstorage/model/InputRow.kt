package com.yggdralisk.booksstorage.model

import com.google.gson.Gson
import io.realm.RealmObject

open class InputRow(
        var labelText: String = "",
        private var inputType: String = InputType.TEXT.toString()
) : RealmObject() {
    companion object {
        fun fromJson(json: String): InputRow {
            val row = Gson().fromJson(json, InputRow::class.java)
            row.labelText = row.labelText.replace("($%)", " ") //Gson has issues with spaces in my typed array, hacky hacky
            return row
        }

        fun toJson(inputRow: InputRow): String {
            inputRow.labelText = inputRow.labelText.replace("($%)", " ")
            return Gson().toJson(inputRow, InputRow::class.java)
        }
    }

    //Realm doesn't support enums as fields, so we have to transform it from/to string
    fun setInputType(inputType: InputType) {
        this.inputType = inputType.toString()
    }

    fun getInputType(): InputType = InputType.valueOf(this.inputType)

    enum class InputType {
        NUMERIC,
        TEXT,
        PHOTO,
        DATE
    }
}