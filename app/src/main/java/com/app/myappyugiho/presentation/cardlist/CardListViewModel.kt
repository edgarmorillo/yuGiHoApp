package com.app.myappyugiho.presentation.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myappyugiho.data.model.Card
import com.app.myappyugiho.data.repository.CardRepository
import kotlinx.coroutines.launch

class CardListViewModel(private val repository: CardRepository) : ViewModel() {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards

    fun loadCardsByArchetype(archetype: String) {
        viewModelScope.launch {
            _cards.clear()
            val cards = repository.getCardsByArchetype(archetype)
            _cards.addAll(cards)
        }
    }
}
