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

data class PokeInfo(
    val id: Int = 0,
    val name: String = "",
    val flavor_text_entries: List<FlavorTextEntry> = listOf(),
)

data class FlavorTextEntry(
    val flavor_text: String = "",
    val language: Language = Language(),
    val version: Version = Version()
)

data class Language(
    val name: String = ""
)

data class Version(
    val name: String = ""
)
