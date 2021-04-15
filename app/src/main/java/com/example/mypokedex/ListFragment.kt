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
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class ListFragment: Fragment() {
    private val adapter = PokeAdapter(getData())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokeRecyclerView.setHasFixedSize(true)
        pokeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        pokeRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: PokeAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int, entry: Int) {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(entry))
            }
        })
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
