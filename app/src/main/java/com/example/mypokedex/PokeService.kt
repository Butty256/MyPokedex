package com.example.mypokedex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {
    @GET("pokemon-form/{id}")
    fun listPokeInfo(
        @Path("id") id: Int
    ): Call<PokeInfo>
}
