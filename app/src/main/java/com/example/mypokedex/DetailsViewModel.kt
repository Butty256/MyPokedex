package com.example.mypokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class DetailsViewModel: ViewModel() {
    val idText = MutableLiveData<String>("0")
    val nameText = MutableLiveData<String>("Missing No.")
    val flavorText = MutableLiveData<String>("")

    fun setData(id: Int) {
        viewModelScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var data: PokeInfo = PokeInfo()
            thread {
                try {
                    val service: PokeInfoService = retrofit.create(PokeInfoService::class.java)
                    data = service.pokeInfo(id).execute().body() ?: throw IllegalStateException("NULL")
                } catch (e: Exception) {
                    Log.d("api", "debug $e")
                }
            }
            // だめだめ，解決できてない
            //delay(500)
            while (data.id == 0) {}
            println(data.id)
            idText.value = "No. " + data.id.toString()
            nameText.value = data.name.capitalize()
            val flavorSize: Int = data.flavor_text_entries.size
            for (i in 0..flavorSize) {
                if (data.flavor_text_entries[i].language == Language("en") &&
                    data.flavor_text_entries[i].version == Version("red")) {
                    flavorText.value = data.flavor_text_entries[i].flavor_text
                    break
                }
            }
        }
    }

    fun setData2(data: PokeInfo) {
        idText.value = data.id.toString()
        nameText.value = data.name.capitalize()
        flavorText.value = data.flavor_text_entries[0].flavor_text
    }
}
