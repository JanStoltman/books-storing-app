package com.yggdralisk.booksstorage.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.model.BookModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getBooks().observe(this, Observer<ArrayList<BookModel>> {
            Timber.d("New books. Size: ${it?.size ?: 0}")
            if (it == null || it.isEmpty()) {
                displayEmptyFragment()
            } else {
                displayListFragment()
            }
        })
    }

    private fun displayEmptyFragment() {
        Timber.d("Creating empty fragment")
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, EmptyFragment.newInstance())
                .commitNow()
    }

    private fun displayListFragment() {
        Timber.d("Creating list fragment")
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commitNow()
    }

}
