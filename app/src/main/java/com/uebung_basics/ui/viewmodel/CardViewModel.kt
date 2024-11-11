package com.uebung_basics.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uebung_basics.data.database.DrawnCardDataBase
import com.uebung_basics.data.database.model.DrawnCard
import com.uebung_basics.data.models.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CardViewModel(private val database: DrawnCardDataBase) : ViewModel() {
    private val drawnCardDao = database.drawnCardDao()

    // Methode zum Speichern der gezogenen Karte in der Datenbank
    fun saveDrawnCard(deckId: String, card: Card) {
        val drawnCard = DrawnCard(
            deckId = deckId,
            cardValue = card.value,
            cardSuit = card.suit
        )
        viewModelScope.launch {
            drawnCardDao.insert(drawnCard)
        }
    }

    // Methode zum Abrufen der letzten 3 gezogenen Karten
    fun getLastThreeDrawnCards(): Flow<List<DrawnCard>> {
        return drawnCardDao.getLastThreeDrawnCards()
    }

    // Methode zum Löschen aller Karten aus der Datenbank (Reset)
    fun resetDeck() {
        viewModelScope.launch {
            drawnCardDao.deleteAllCards()
        }
    }
}