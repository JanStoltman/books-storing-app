package com.yggdralisk.booksstorage.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.model.BookModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getBooks().observe(this, Observer<ArrayList<BookModel>> {
            if (it == null || it.isEmpty()) {
                displayEmptyFragment()
            } else {
                displayListFragment()
            }
        })
    }

    fun displayEmptyFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, EmptyFragment.newInstance())
                .commitNow()
    }

    fun displayListFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commitNow()
    }

}
