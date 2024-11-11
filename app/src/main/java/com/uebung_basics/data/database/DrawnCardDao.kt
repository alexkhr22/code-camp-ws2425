package com.uebung_basics.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uebung_basics.data.database.model.DrawnCard
import kotlinx.coroutines.flow.Flow

@Dao
interface DrawnCardDao {
    // Füge eine gezogene Karte in die Datenbank ein
    @Insert
    suspend fun insert(drawnCard: DrawnCard)

    // Hole die letzten 3 gezogenen Karten
    @Query("SELECT * FROM drawn_cards ORDER BY id DESC LIMIT 3")
    fun getLastThreeDrawnCards(): Flow<List<DrawnCard>>

    // Lösche alle Karten aus der Datenbank
    @Query("DELETE FROM drawn_cards")
    suspend fun deleteAllCards()
}