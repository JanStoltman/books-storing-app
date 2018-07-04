package com.yggdralisk.booksstorage.ui.add.adapter

import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.yggdralisk.booksstorage.R
import com.yggdralisk.booksstorage.model.InputRow
import com.yggdralisk.booksstorage.model.InputRow.InputType.*
import com.yggdralisk.booksstorage.ui.add.provider.AddBookProvider

class AddBookRecyclerViewAdapter(private val provider: AddBookProvider) : RecyclerView.Adapter<AddBookRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.input_row_recycler_view_row, parent, false))

    override fun getItemCount(): Int =
            provider.getDataSize()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindData(provider.getInputRow(position))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var inputRow: InputRow? = null

        fun bindData(inputRow: InputRow?) {
            inputRow?.let {
                this.inputRow = it
                itemView.findViewById<TextView>(R.id.labelTextView).text = inputRow.labelText
                itemView.findViewById<FrameLayout>(R.id.inputContainer).addView(createInput(it.getInputType()))
            }
        }

        private fun createInput(inputType: InputRow.InputType): View = when (inputType) {
            NUMERIC -> {
                val view = EditText(itemView.context)
                view.layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT)
                view.gravity   = Gravity.CENTER
                view.inputType = InputType.TYPE_CLASS_NUMBER
                view
            }
            TEXT -> {
                val view = EditText(itemView.context)
                view.layoutParams = LinearLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT)
                view.gravity = Gravity.CENTER
                view.inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                view
            }
            PHOTO -> {
                val view = ImageView(itemView.context)
                view.layoutParams = LinearLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT)
                view.setImageResource(R.drawable.ic_add_photo)
                view
            }
            DATE -> {
                val view = DatePicker(itemView.context)
                view.layoutParams = LinearLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT)
                view
            }
        }

        fun validateRow() {

        }

    }
}