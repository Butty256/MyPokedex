package com.example.mypokedex

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class PokeRepository {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(moshiConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val pokeService: PokeService = retrofit.create(PokeService::class.java)

    suspend fun getPokeInfo(id: Int): Response<PokeInfo> {
        return pokeService.getPokeInfo(id)
    }

    companion object Factory {
        val instance: PokeRepository
            @Synchronized get() {
                return PokeRepository()
            }
    }
}