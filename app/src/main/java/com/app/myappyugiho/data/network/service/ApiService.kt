package com.app.myappyugiho.data.network.service

import com.app.myappyugiho.data.network.response.CardResponse
import com.app.myappyugiho.data.network.response.BanListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
         const val BASE_URL = "https://db.ygoprodeck.com/api/v7/"
    }

    @GET("cardinfo.php")
    suspend fun getCards(
        @Query("archetype") archetype: String?,
        @Query("banlist") banlist: String = "tcg"

    ): CardResponse

    @GET("banlist.php")
    suspend fun getBanList(): BanListResponse
}