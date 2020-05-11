package com.soffanma.booksample.presenter

import android.util.Log
import com.soffanma.booksample.model.Book
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

}