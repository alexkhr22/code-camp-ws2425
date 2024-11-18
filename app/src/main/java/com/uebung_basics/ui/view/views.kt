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
import kotlinx.coroutines.launch


@Composable
fun HomeView(deckViewModel: DeckViewModel = viewModel()) {
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
            val deckId = deckViewModel.deckId

            if (deckId != null) {
                deckViewModel.viewModelScope.launch {
                    // Ziehe eine Karte und warte auf das Ergebnis
                    deckViewModel.drawCards(1)

                    // Überprüfe, ob Karten gezogen wurden
                    val drawnCard = deckViewModel.drawnCards.value.firstOrNull()
                    if (drawnCard != null) {
                        // Speichern der gezogenen Karte in der Datenbank
                        Log.d("CardDraw", "Gezogene Karte: ${drawnCard.value} of ${drawnCard.suit}")
                    } else {
                        Log.d("CardDraw", "Keine Karte gezogen. Überprüfe die Deck-ID oder versuche es später.")
                    }
                }
            } else {
                Log.d("CardDraw", "Deck wurde noch nicht erstellt.")
            }
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Draw Card")
        }

        Button(onClick = {
            deckViewModel.resetDeck()  // Reset im DeckViewModel (setzt Deck ID zurück)
            // Reset im CardViewModel (löscht alle Karten aus der DB)
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