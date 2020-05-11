package com.soffanma.booksample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BookItem : Serializable {

    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("name")
    val name: String? = null

    @field:SerializedName("author")
    val author: String? = null

    @field:SerializedName("published_at")
    val published_at: String? = null

}