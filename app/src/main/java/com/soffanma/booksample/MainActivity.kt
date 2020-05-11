package com.soffanma.booksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soffanma.booksample.adapter.BookAdapter
import com.soffanma.booksample.model.BookItem
import com.soffanma.booksample.presenter.CrudView
import com.soffanma.booksample.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this)
        presenter.getData()
    }

    override fun onSuccessGet(data: List<BookItem>?) {
        rvBook.adapter = BookAdapter(data, object : BookAdapter.onClickItem{
            override fun clicked(item: BookItem?) {
                startActivity<UpdateAddActivity>("bookItem" to item)
            }

            override fun delete(item: BookItem?) {

            }
        })
    }

    override fun onFailedGet(msg: String) {

    }

}
