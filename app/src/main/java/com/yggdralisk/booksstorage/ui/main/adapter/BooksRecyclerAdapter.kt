package com.yggdralisk.booksstorage.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.model.BookModel
import com.yggdralisk.booksstorage.ui.main.provider.BooksProvider

class BooksRecyclerAdapter(val provider: BooksProvider) : RecyclerView.Adapter<BooksRecyclerAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
            BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_recycler_row, parent, false))

    override fun getItemCount(): Int =
            provider.getDataSize()

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
            holder.bindBook(provider.getBook(position))

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindBook(book: BookModel?) {
            book?.let {
                itemView.findViewById<TextView>(R.id.bookTitle).text = book.title
            }
        }
    }
}