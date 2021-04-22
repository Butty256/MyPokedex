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

    private val repository = PokeRepository.instance

    fun setData(id: Int) {
        viewModelScope.launch {
            try {
                val data = repository.getPokeInfo(id)
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
            } catch (e: Exception) {
                Log.d("api", "debug $e")
            }
        }
    }
}
