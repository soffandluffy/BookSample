package com.soffanma.booksample.presenter

import com.soffanma.booksample.model.BookItem

interface CrudView {

    // Get data
    fun onSuccessGet(data: List<BookItem>?)
    fun onFailedGet(msg: String)

    // Delete
    fun onSucessDelete(msg: String)
    fun onErrorDelete(msg: String)

    // Add
    fun onSuccessAdd(msg: String)
    fun onErrorAdd(msg: String)

    // Update
    fun onSucessUpdate(msg: String)
    fun onErrorUpdate(msg: String)

}