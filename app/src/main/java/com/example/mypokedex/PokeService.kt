package com.example.mypokedex

import retrofit2.Call
import retrofit2.http.GET

interface PokeService {
    @GET("pokemon-shape/1")
    fun listPokeInfo(): Call<PokeInfo>
}
