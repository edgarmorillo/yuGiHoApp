package com.app.myappyugiho.presentation.carddetail



import androidx.lifecycle.ViewModel
import com.app.myappyugiho.data.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CardDetailViewModel : ViewModel() {

    // StateFlow para la carta actualmente mostrada en pantalla
    private val _currentCard = MutableStateFlow<Card?>(null)
    val currentCard: StateFlow<Card?> = _currentCard

    // Función para establecer la carta actual
    fun setCurrentCard(card: Card) {
        _currentCard.value = card
    }
}
