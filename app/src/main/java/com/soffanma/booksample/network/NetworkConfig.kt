package com.soffanma.booksample.network

import com.soffanma.booksample.model.Book
import com.soffanma.booksample.model.BookItem
import com.soffanma.booksample.model.BookStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging  = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    //Retrofit
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.12/api/books/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(BookService::class.java)

}

interface BookService {

    // Store data
    @FormUrlEncoded
    @POST("store")
    fun store(@Field("name") name : String,
              @Field("author") author : String,
              @Field("published_at") published_at : String) : Call<BookStatus>

    // Get Data
    @GET("index")

    fun getData() : Call<Book>

    // Delete
    @FormUrlEncoded
    @POST("destroy")
    fun delete(@Field("id") id : String?) : Call<BookStatus>

    // Update
    @FormUrlEncoded
    @POST("update")
    fun update(@Field("id") id : String,
              @Field("name") name : String,
              @Field("author") author : String,
              @Field("published_at") published_at : String) : Call<BookStatus>

}