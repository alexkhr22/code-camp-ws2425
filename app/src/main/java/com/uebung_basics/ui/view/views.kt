package com.uebung_basics.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uebung_basics.ui.viewmodel.DeckViewModel
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.uebung_basics.ui.viewmodel.CardViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeView(deckViewModel: DeckViewModel = viewModel(), cardViewModel: CardViewModel = viewModel()) {
    val drawnCards by deckViewModel.drawnCards.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Buttons im HomeView, die auf Klick reagieren
        Button(onClick = { deckViewModel.createNewDeck(1) }) {
            Text(text = "Get New Deck")
        }

        Button(onClick = {
            // Stelle sicher, dass ein Deck existiert und gezogen werden kann
            val deckId = deckViewModel.deckId

            if (deckId != null) {
                // Zieht eine Karte vom Server
                deckViewModel.drawCards(1)

                // Holt sich die erste gezogene Karte, falls vorhanden
                val drawnCard = deckViewModel.drawnCards.value.firstOrNull()

                drawnCard?.let { card ->
                    // Speichern der gezogenen Karte in der Datenbank
                    cardViewModel.saveDrawnCard(deckId, card)
                    Log.d("CardDraw", "Gezogene Karte: ${card.value} of ${card.suit}")
                } ?: Log.d("CardDraw", "Keine Karte gezogen. Überprüfe die Deck-ID oder versuche es später.")
            } else {
                Log.d("CardDraw", "Deck wurde noch nicht erstellt.")
            }
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Draw Card")
        }

        Button(onClick = {
            // Startet eine Coroutine im ViewModel-Scope
            deckViewModel.viewModelScope.launch {
                cardViewModel.getLastThreeDrawnCards().collect { cards ->
                    cards.forEach {
                        Log.d("LastCard", "Letzte Karte: ${it.cardValue} of ${it.cardSuit}")
                    }
                }
            }
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Get Last Card")
        }

        Button(onClick = {
            deckViewModel.resetDeck()  // Reset im DeckViewModel (setzt Deck ID zurück)
            cardViewModel.resetDeck()  // Reset im CardViewModel (löscht alle Karten aus der DB)
            Log.d("CardDraw", "Deck und Karten wurden zurückgesetzt.")
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Reset")
        }

        // Anzeigen der gezogenen Karten
        drawnCards.forEach { card ->
            // Hier kannst du die Logik zum Anzeigen der Kartenbilder implementieren.
            // Zum Beispiel:
            Image(painter = rememberAsyncImagePainter(card.image), contentDescription = card.value)
        }
    }
}
@Composable
fun SettingsView(){
    Text(text = "Settings Screen")
}

@Composable
fun ProfileView(){
    Text(text = "Profile Screen")
}