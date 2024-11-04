package com.uebung_basics.network
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.uebung_basics.data.remote.DeckOfCardsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//BaseURL für die Retrofitinstance
object RetrofitInstance {
    private const val BASE_URL = "https://deckofcardsapi.com/"

    val api: DeckOfCardsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(DeckOfCardsApiService::class.java)
    }
}