package com.soffanma.booksample.presenter

import com.soffanma.booksample.model.BookItem

interface CrudView {

    // Get data
    fun onSuccessGet(data: List<BookItem>?)
    fun onFailedGet(msg: String)

}