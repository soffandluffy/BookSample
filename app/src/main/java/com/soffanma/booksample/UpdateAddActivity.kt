package com.soffanma.booksample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soffanma.booksample.model.BookItem
import com.soffanma.booksample.presenter.CrudView
import com.soffanma.booksample.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_add.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class UpdateAddActivity : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)

        presenter = Presenter(this)
        val bookItem = intent.getSerializableExtra("bookItem")
        if (bookItem == null){
            btnAction.text = "Add"
            btnAction.onClick {
                presenter.store(
                    etName.text.toString(),
                    etAuthor.text.toString(),
                    etPublished.text.toString()
                )
            }
        } else if (bookItem != null) {
            btnAction.text = "Update"
            val item = bookItem as BookItem?
            etName.setText(item?.name.toString())
            etAuthor.setText(item?.author.toString())
            etPublished.setText(item?.published_at.toString())
            btnAction.onClick {
                presenter.update(
                    item?.id ?: "",
                    etName.text.toString(),
                    etAuthor.text.toString(),
                    etPublished.text.toString()
                )
                finish()
            }
        }

    }

    override fun onSuccessAdd(msg: String) {
        startActivity<MainActivity>()
        finish()
    }

    override fun onErrorAdd(msg: String) {

    }

    override fun onSucessUpdate(msg: String) {
        startActivity<MainActivity>()
        finish()
    }

    override fun onErrorUpdate(msg: String) {

    }

    override fun onSuccessGet(data: List<BookItem>?) {

    }

    override fun onFailedGet(msg: String) {

    }

    override fun onSucessDelete(msg: String) {

    }

    override fun onErrorDelete(msg: String) {

    }

}
