package com.uebung_basics.data.remote

import com.uebung_basics.data.models.DeckResponse
import com.uebung_basics.data.models.DrawResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Serveranfragen
interface DeckOfCardsApiService {
    //Create new deck
    @GET("api/deck/new/shuffle/")
    suspend fun createNewDeck(
            @Query("deck_count") deckCount: Int = 1
    ): DeckResponse

    //Draw cards
    @GET("api/deck/{deck_id}/draw/")
    suspend fun drawCards(
        @Path("deck_id") deckId: String,
        @Query("count") count: Int
    ): DrawResponse
}