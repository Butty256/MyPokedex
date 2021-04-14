package com.example.mypokedex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {
    @GET("pokedex/{name}")
    fun listPokeInfo(
        @Path("name") name: String
    ): Call<DexInfo>
}
