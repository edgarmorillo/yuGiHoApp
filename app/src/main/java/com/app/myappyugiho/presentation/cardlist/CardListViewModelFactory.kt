package com.app.myappyugiho.presentation.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.myappyugiho.data.repository.CardRepository
import com.app.myappyugiho.presentation.cardlist.CardListViewModel

//class CardListViewModelFactory(private val repository: CardRepository) : ViewModelProvider.Factory
class CardListViewModelFactory(private val repository: CardRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
