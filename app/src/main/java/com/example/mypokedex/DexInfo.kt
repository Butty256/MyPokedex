package com.example.mypokedex

data class DexInfo(
    val id: Int = 0,
    val name: String = "",
    val pokemon_entries: List<PokemonEntry> = listOf()
)

data class PokemonEntry(
    val entry_number: Int = 0,
    val pokemon_species: PokemonSpecies = PokemonSpecies(),
)

data class PokemonSpecies(
    val name: String = ""
)
