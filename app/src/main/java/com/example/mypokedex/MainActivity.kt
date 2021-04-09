package com.example.mypokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        viewModel.listPokeInfo()
        val pokeApi = PokeApiClient()
        //val poke = pokeApi.getPokemonShape(1)
    }
}