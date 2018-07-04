package com.yggdralisk.booksstorage.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding2.view.RxView
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.ui.add.AddBookActivity
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class EmptyFragment : Fragment() {
    companion object {
        fun newInstance() = EmptyFragment()
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable.add(RxView.clicks(view.findViewById(R.id.emptyTextView))
                .takeUntil(RxView.detaches(view))
                .subscribe { startAddBookActivity() })
    }

    private fun startAddBookActivity() =
            startActivity(Intent(context, AddBookActivity::class.java))

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
