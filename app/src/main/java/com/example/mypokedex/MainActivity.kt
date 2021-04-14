package com.example.mypokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val adapter = PokeAdapter(getData())
    }

    private fun getData(): DexInfo {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var data: DexInfo = DexInfo()
        thread {
            try {
                val service: PokeService = retrofit.create(PokeService::class.java)
                data = service.listPokeInfo("kanto").execute().body() ?: throw IllegalStateException("NULL")
            } catch (e: Exception) {
                Log.d("api", "debug $e")
            }
        }
        // 一時的に止まる
        // 本当はスレッドが終わるまで待ちたい
        Thread.sleep(1000)
        return data
    }
}
