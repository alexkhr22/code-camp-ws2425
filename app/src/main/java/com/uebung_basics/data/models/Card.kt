package com.uebung_basics.data.models

//Models zum Abspeicher der Serverantworten
data class Card(
    val code: String,
    val image: String,
    val images: Images,
    val value: String,
    val suit: String
)

data class Images(
    val svg: String,
    val png: String
)