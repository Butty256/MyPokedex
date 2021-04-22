package com.example.mypokedex

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PokeRepository {

    private val pokeService: PokeService by lazy {

        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(moshiConverterFactory)
                .build()

        retrofit.create(PokeService::class.java)
    }

    suspend fun getPokeDex(name: String): PokeDex {
        return pokeService.getPokeDex(name).body() ?: throw IllegalStateException("NULL")
    }

    suspend fun getPokeInfo(id: Int): PokeInfo {
        return pokeService.getPokeInfo(id).body() ?: throw IllegalStateException("NULL")
    }

    companion object Factory {
        val instance: PokeRepository
            @Synchronized get() {
                return PokeRepository()
            }
    }
}