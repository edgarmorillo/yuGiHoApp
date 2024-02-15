package com.app.myappyugiho.data.repository

import com.app.myappyugiho.data.model.BanListItem
import com.app.myappyugiho.data.model.Card
import com.app.myappyugiho.data.network.service.ApiService

class CardRepository(private val apiService: ApiService) {
    suspend fun getCardsByArchetype(archetype: String): List<Card> {
        val banList = getBanListFromApi()
        val cards = apiService.getCards(archetype).cards
        return cards.filterNot { card -> banList.any { it.name == card.name } }
    }

    private suspend fun getBanListFromApi(): List<BanListItem> {
        return apiService.getBanList().banListItems
    }
}
