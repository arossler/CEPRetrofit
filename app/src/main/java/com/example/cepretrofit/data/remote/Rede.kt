package com.example.cepretrofit.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Rede {
    companion object{
        fun getRetrofitInstance(url: String): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}