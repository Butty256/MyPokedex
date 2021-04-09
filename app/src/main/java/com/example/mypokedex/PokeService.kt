package com.example.mypokedex

import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {
    @GET("pokemon/{id}")
    suspend fun listPokeInfo(
        @Path("num") num: String
    ): List<PokeInfo>
}