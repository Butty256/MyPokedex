package com.example.mypokedex

data class PokeInfo(
    val awesome_names: List<AwesomeName>,
    val id: Int,
    val name: String,
    val names: List<PokeName>,
    val pokemon_species: List<PokeSpecies>
)

data class AwesomeName(
    val awesome_name: String,
    val language: Language
)

data class Language(
    val name: String,
    val url: String
)

data class PokeName(
    val language: Language,
    val name: String
)

data class PokeSpecies(
    val name: String,
    val url: String
)