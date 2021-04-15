package com.example.mypokedex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeInfoService {
    @GET("pokemon-species/{id}")
    fun pokeInfo(
        @Path("id") id: Int
    ): Call<PokeInfo>
}
