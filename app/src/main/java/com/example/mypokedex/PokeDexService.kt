package com.example.mypokedex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeDexService {
    @GET("pokedex/{name}")
    fun listPoke(
        @Path("name") name: String
    ): Call<DexInfo>
}
