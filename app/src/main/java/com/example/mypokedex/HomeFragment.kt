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
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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

        val repository = PokeRepository.instance

        var data = PokeDex()

        runBlocking {
            try {
                data = repository.getPokeDex("kanto")
            } catch (e: Exception) {
                Log.d("api", "debug $e")
            }
        }

        return data
    }
}
