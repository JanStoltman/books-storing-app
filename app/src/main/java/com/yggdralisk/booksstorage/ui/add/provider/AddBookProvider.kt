package com.yggdralisk.booksstorage.ui.add.provider

import com.yggdralisk.booksstorage.model.InputRow

interface AddBookProvider {
    fun getInputRows(): ArrayList<InputRow>

    fun getDataSize(): Int

    fun getInputRow(index: Int): InputRow?
}