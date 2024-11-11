package com.uebung_basics.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uebung_basics.data.models.Card
import com.uebung_basics.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//Methoden für Serveranfragen und Abspeicher von Attributen
class DeckViewModel: ViewModel() {
    var deckId: String? = null
    private val _drawnCards = MutableStateFlow<List<Card>>(emptyList())
    val drawnCards: StateFlow<List<Card>> get() = _drawnCards

    fun createNewDeck(deckCount: Int) {
        viewModelScope.launch {
            val response = RetrofitInstance.api.createNewDeck(deckCount)
            deckId = response.deck_id
            println("Neues Deck erstellt mit ID: ${deckId}")
        }
    }

    fun drawCards(count: Int) {
        viewModelScope.launch {
            deckId?.let {
                val response = RetrofitInstance.api.drawCards(it, count)
                _drawnCards.value = response.cards
                response.cards.forEach { card ->
                    println("Gezogene Karte: ${card.value} of ${card.suit}")
                }
            } ?: println("Deck wurde noch nicht erstellt.")
        }
    }

    fun resetDeck() {
        deckId = null
        _drawnCards.value = emptyList()
        println("Deck und Handkarten wurden zurückgesetzt.")
    }
}