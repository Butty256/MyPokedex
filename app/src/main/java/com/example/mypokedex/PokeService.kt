package com.example.mypokedex

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {

    @GET("pokedex/{name}")
    fun getPokeDex(@Path("name") name: String): Call<PokeDex>

    @GET("pokemon-species/{id}")
    suspend fun getPokeInfo(@Path("id") id: Int): Response<PokeInfo>
}