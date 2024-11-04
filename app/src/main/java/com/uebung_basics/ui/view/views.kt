package com.uebung_basics.ui.view

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
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter


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
        Button(onClick = { deckViewModel.drawCards(1) }, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Draw Cards")
        }
        Button(onClick = { deckViewModel.resetDeck() }, modifier = Modifier.padding(top = 8.dp)) {
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