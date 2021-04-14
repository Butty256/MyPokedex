package com.example.mypokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val handler = Handler()

        thread {
            try {
                val service: PokeService = retrofit.create(PokeService::class.java)
                val poke = service.listPokeInfo(25).execute().body() ?: throw IllegalStateException("NULL!!!!")
                handler.post(Runnable {
                    println(poke.sprites.back_default)
                })
            } catch (e: Exception) {
                Log.d("mopi", "debug $e")
            }
        }
    }
}