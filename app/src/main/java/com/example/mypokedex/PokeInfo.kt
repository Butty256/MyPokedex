package com.example.mypokedex

import com.squareup.moshi.Json

data class PokeInfo(
    @Json(name = "num")
    val num: String
)
