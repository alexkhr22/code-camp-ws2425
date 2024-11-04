package com.uebung_basics.data.models

data class DrawResponse(
    val success: Boolean,
    val deck_id: String,
    val cards: List<Card>,
    val remaining: Int
)
