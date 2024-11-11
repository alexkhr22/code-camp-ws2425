package com.uebung_basics.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drawn_cards")
data class DrawnCard(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "deck_id") val deckId: String,
    @ColumnInfo(name = "card_value") val cardValue: String,
    @ColumnInfo(name = "card_suit") val cardSuit: String
)