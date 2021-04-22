package com.example.mypokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DetailsViewModel: ViewModel() {

    val idText = MutableLiveData<String>("0")
    val nameText = MutableLiveData<String>("Missing No.")
    val flavorText = MutableLiveData<String>("")

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

    private val repository = PokeRepository.instance

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(moshiConverterFactory)
        .build()

    private val pokeService: PokeService = retrofit.create(PokeService::class.java)

    fun setData(id: Int) {
        viewModelScope.launch {
            try {
                val data = repository.getPokeInfo(id)
                //val data = request.execute().body() ?: throw IllegalStateException("NULL")
                //val data = repository.getPokeInfo(id)
                idText.value = "No. " + data.id.toString()
                Log.d("api", "debug OK!!!!!")
            } catch (e: Exception) {
                Log.d("api", "debug $e")
            }
            Thread.sleep(1000)
            println("launch")
        }
        println("soto")
    }

    fun setDataa(id: Int) {
        viewModelScope.launch {
            println("scope1")
            Thread.sleep(2000)
            println("scope2")
        }
        println("soto")
        viewModelScope.launch {
            //var data: PokeInfo = PokeInfo()
            try {
                val request = repository.getPokeInfo(id)
                //val data = request.execute().body() ?: throw IllegalStateException("NULL")
                //val data = repository.getPokeInfo(id)
                //idText.value = "No. " + data.id.toString()
                Log.d("api", "debug OK!!!!!")
            } catch (e: Exception) {
                Log.d("api", "debug $e")
            }
            // だめだめ，解決できてない
            //while (data.id == 0) {}
            //idText.value = "No. " + data.id.toString()
            //nameText.value = data.name.capitalize()
            //val flavorSize: Int = data.flavor_text_entries.size
            //for (i in 0..flavorSize) {
            //    if (data.flavor_text_entries[i].language == Language("en") &&
            //        data.flavor_text_entries[i].version == Version("red")) {
            //        flavorText.value = data.flavor_text_entries[i].flavor_text
            //        break
            //    }
            //}
        }
    }
}
