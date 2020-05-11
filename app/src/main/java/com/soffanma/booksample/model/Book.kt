package com.soffanma.booksample.model

import com.google.gson.annotations.SerializedName

data class Book (

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val book: List<BookItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null

)