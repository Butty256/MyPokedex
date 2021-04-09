package com.example.mypokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class MainViewModel : ViewModel() {
    private val service: PokeService by lazy {
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

    fun listPokeInfo() {
        viewModelScope.launch {
            try {
                val info = service.listPokeInfo("1")
                //info.forEach {
                //    Log.d(
                //        "HTTPExample",
                //        "fullName: ${it.num}"
                //    )
                //}
            } catch (e: IOException) {
                Log.e("HTTPExample", "Network Error")
            } catch (e: HttpException) {
                Log.e("HTTPExample", "API Error")
            }
        }
    }
}