package com.example.cepretrofit.data.remote

import com.example.cepretrofit.data.models.CEP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostmonAPI{

    @GET("v1/cep/{cep}")
    fun getCEPInfo (@Path("cep") cep:String): Call<CEP>
}