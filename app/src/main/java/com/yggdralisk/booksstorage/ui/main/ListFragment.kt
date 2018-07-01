package com.yggdralisk.booksstorage.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yggdralisk.booksstorage.R

class ListFragment : Fragment() {

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
    }

    private fun getMainViewModel(): MainViewModel {
        return if (activity != null) {
            ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        } else {
            ViewModelProviders.of(this).get(MainViewModel::class.java) //Just to be sure in edge cases

        }
    }

}
