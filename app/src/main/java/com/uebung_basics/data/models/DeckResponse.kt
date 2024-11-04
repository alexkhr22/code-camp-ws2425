package com.uebung_basics.data.models

data class DeckResponse(
    val success: Boolean,
    val deck_id: String,
    val shuffled: Boolean,
    val remaining: Int
)