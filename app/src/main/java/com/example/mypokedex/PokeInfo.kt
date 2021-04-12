package com.example.mypokedex

data class PokeInfo(
    //val form_name: String,
    //val form_names: List<Name>,
    //val form_order: Int,
    //val id: Int,
    //val is_battle_only: Boolean,
    //val is_default: Boolean,
    //val is_mega: Boolean,
    //val name: String,
    //val names: List<Name>,
    //val order: Int,
    //val pokemon: Pokemon,
    val sprites: Sprite,
    //val types: List<Types>,
    //val version_group: VersionGroup
)

data class Name(
    val language: Language,
    val name: String
)

data class Language(
    val name: String,
    val url: String
)

data class Pokemon(
    val name: String,
    val url: String
)

data class Sprite(
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String
)

data class Types(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)

data class VersionGroup(
    val name: String,
    val url: String
)