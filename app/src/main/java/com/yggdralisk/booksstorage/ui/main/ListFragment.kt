package com.yggdralisk.booksstorage.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.model.BookModel
import com.yggdralisk.booksstorage.ui.main.adapter.BooksRecyclerAdapter
import com.yggdralisk.booksstorage.ui.main.provider.BooksProvider
import kotlinx.android.synthetic.main.fragment_books_list.*
import timber.log.Timber

class ListFragment : Fragment(), BooksProvider {
    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_books_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getMainViewModel()
        viewModel.getBooks().observe(this, Observer<ArrayList<BookModel>> {
            Timber.d("New books. Size: ${it?.size ?: 0}")
            booksRecyclerView.adapter?.notifyDataSetChanged()
        })

        booksRecyclerView.layoutManager = LinearLayoutManager(context)
        booksRecyclerView.adapter = BooksRecyclerAdapter(this)
    }

    private fun getMainViewModel(): MainViewModel {
        return if (activity != null) {
            ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        } else {
            ViewModelProviders.of(this).get(MainViewModel::class.java) //Just to be sure in edge cases
        }
    }

    override fun getBooks(): ArrayList<BookModel> = viewModel.getBooks().value ?: arrayListOf()

    override fun getDataSize(): Int = viewModel.getBooks().value?.size ?: 0

    override fun getBook(index: Int): BookModel? = viewModel.getBooks().value?.get(index)
}
