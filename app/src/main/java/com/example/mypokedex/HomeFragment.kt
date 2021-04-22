package com.example.mypokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.concurrent.thread

class HomeFragment: Fragment() {

    private val adapter = HomeAdapter(getData())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokeRecyclerView.setHasFixedSize(true)
        pokeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        pokeRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: HomeAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int, entry: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(entry))
            }
        })
    }

    private fun getData(): PokeDex {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val moshiConverterFactory = MoshiConverterFactory.create(moshi)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(moshiConverterFactory)
            .build()

        val service: PokeService = retrofit.create(PokeService::class.java)

        var data: PokeDex = PokeDex()
        thread {
            try {
                data = service.getPokeDex("kanto").execute().body() ?: throw IllegalStateException("NULL")
            } catch (e: Exception) {
                Log.d("api", "debug $e")
            }
        }
        // 一時的に止まる
        // 本当はスレッドが終わるまで待ちたい
        Thread.sleep(2000)
        return data
    }
}
