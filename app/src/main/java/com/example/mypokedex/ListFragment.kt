package com.example.mypokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class ListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokeAdapter(createDataArray())

        pokeRecyclerView.setHasFixedSize(true)
        pokeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        pokeRecyclerView.adapter = adapter
    }

    private fun createDataArray(): Array<PokeImage> {
        val dataArray: Array<PokeImage> = Array(200) {PokeImage()}
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: PokeService = retrofit.create(PokeService::class.java)

        for (i in 0..151) {
            thread {
                try {
                    val poke = service.listPokeInfo(i).execute().body() ?: throw IllegalStateException("NULL!!!!")
                    val data= PokeImage().also {
                        it.name = poke.name
                        it.id = poke.id
                        it.url = poke.sprites.front_default
                    }
                    dataArray[i] = data
                } catch (e: Exception) {
                    Log.d("api", "debug $e")
                }
            }
        }
        return dataArray
    }
}