package com.soffanma.booksample.presenter

import android.util.Log
import com.soffanma.booksample.model.Book
import com.soffanma.booksample.model.BookStatus
import com.soffanma.booksample.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter (val crudView: CrudView) {

    // Getdata
    fun getData(){
        NetworkConfig.getService().getData()
            .enqueue(object : retrofit2.Callback<Book>{
                override fun onFailure(call: Call<Book>, t: Throwable) {
                    crudView.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Data error")
                }

                override fun onResponse(call: Call<Book>, response: Response<Book>) {
                    if (response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.book
                            crudView.onSuccessGet(data)
                        } else {
                            crudView.onFailedGet("Error $status")
                        }
                    }
                }

            })
    }

    // Store
    fun store(name : String, author : String, published_at : String){
        NetworkConfig.getService()
            .store(name,author,published_at)
            .enqueue(object : retrofit2.Callback<BookStatus>{
                override fun onFailure(call: Call<BookStatus>, t: Throwable) {
                    crudView.onErrorAdd(t.localizedMessage)
                }

                override fun onResponse(call: Call<BookStatus>, response: Response<BookStatus>) {
                    if(response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessAdd(response.body()?.message ?: "")
                    } else {
                        crudView.onErrorAdd(response.body()?.message ?: "")
                    }
                }
            })
    }

    // Delete
    fun delete(id: String?){
        NetworkConfig.getService()
            .delete(id)
            .enqueue(object : retrofit2.Callback<BookStatus>{
                override fun onFailure(call: Call<BookStatus>, t: Throwable) {
                    crudView.onErrorDelete(t.localizedMessage)
                }

                override fun onResponse(call: Call<BookStatus>, response: Response<BookStatus>) {
                    if(response.isSuccessful && response.body()?.status == 200){
                        crudView.onSucessDelete(response.body()?.message ?: "")
                    } else {
                        crudView.onErrorDelete(response.body()?.message ?: "")
                    }
                }
            })
    }

    // Update
    fun update(id: String, name : String, author : String, published_at: String){
        NetworkConfig.getService()
            .update(id,name,author,published_at)
            .enqueue(object : retrofit2.Callback<BookStatus>{
                override fun onFailure(call: Call<BookStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage)
                }

                override fun onResponse(call: Call<BookStatus>, response: Response<BookStatus>) {
                    if(response.isSuccessful && response.body()?.status == 200){
                        crudView.onSucessUpdate(response.body()?.message ?: "")
                    } else {
                        crudView.onErrorUpdate(response.body()?.message ?: "")
                    }
                }
            })
    }

}