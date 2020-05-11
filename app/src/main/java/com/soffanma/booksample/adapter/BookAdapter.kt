package com.soffanma.booksample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.soffanma.booksample.MainActivity
import com.soffanma.booksample.R
import com.soffanma.booksample.model.BookItem
import kotlinx.android.synthetic.main.book_data.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class BookAdapter(val data: List<BookItem>?, private val click: onClickItem) : RecyclerView.Adapter<BookAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_data, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.onClick {
            click.clicked(data?.get(position))
        }
        holder.itemView.btnDelete.setOnClickListener {

        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(get: BookItem?) {
            itemView.tvName.text = get?.name
            itemView.tvAuthor.text = get?.author
            itemView.tvPublished.text = get?.published_at
        }
    }

    interface onClickItem {
        fun clicked(item: BookItem?)
        fun delete(item: BookItem?)
    }

}