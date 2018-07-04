package com.yggdralisk.booksstorage.ui.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.model.InputRow
import com.yggdralisk.booksstorage.ui.add.adapter.AddBookRecyclerViewAdapter
import com.yggdralisk.booksstorage.ui.add.provider.AddBookProvider
import kotlinx.android.synthetic.main.add_book_activity.*
import androidx.recyclerview.widget.DividerItemDecoration



class AddBookActivity : AppCompatActivity(), AddBookProvider {
    lateinit var viewModel: AddBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book_activity)
        viewModel = ViewModelProviders.of(this).get(AddBookViewModel::class.java)
        viewModel.getInputRows(application).observe(this, Observer<ArrayList<InputRow>> {
            formRecyclerView.adapter?.notifyDataSetChanged()
        })

        val linearManager = LinearLayoutManager(this)
        formRecyclerView.layoutManager = linearManager
        formRecyclerView.adapter = AddBookRecyclerViewAdapter(this)

        val dividerItemDecoration = DividerItemDecoration(formRecyclerView.context, linearManager.orientation)
        dividerItemDecoration.setDrawable(applicationContext.getDrawable(R.drawable.abc_list_divider_material))
        formRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun getInputRows(): ArrayList<InputRow> = viewModel.getInputRows(application).value ?: arrayListOf()

    override fun getDataSize(): Int = viewModel.getInputRows(application).value?.size ?: 0

    override fun getInputRow(index: Int): InputRow? = viewModel.getInputRows(application).value?.get(index)
}