package com.app.myappyugiho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.myappyugiho.presentation.carddetail.CardDetailScreen
import com.app.myappyugiho.presentation.cardlist.CardListScreen
import com.app.myappyugiho.presentation.common.MyAppYugihoTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.myappyugiho.data.repository.CardRepository
import com.app.myappyugiho.presentation.cardlist.CardListViewModel
import com.app.myappyugiho.presentation.cardlist.CardListViewModelFactory
import com.app.myappyugiho.data.network.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.ViewModelProvider.Factory



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Crear una instancia de ApiService
        val apiService = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        // Crear instancia de CardRepository y pasarle ApiService
        val cardRepository = CardRepository(apiService)
        setContent {
            //MyAppYugihoApp()
            MyAppYugihoApp(cardRepository)
        }
    }
}

// fun MyAppYugihoApp()
@Composable
fun MyAppYugihoApp(cardRepository: CardRepository){
    MyAppYugihoTheme {
        //val viewModel: CardListViewModel = viewModel()

        //val viewModel: CardListViewModel = viewModel(
        //    factory = CardListViewModelFactory(cardRepository)
        //)

        //val viewModel: CardListViewModel = viewModel(factory = CardListViewModelFactory(cardRepository) as ViewModelProvider.Factory)

        val viewModel: CardListViewModel = viewModel(factory = CardListViewModelFactory(cardRepository))


        // Configurar NavController para la navegación
        val navController = rememberNavController()

        // Definir las rutas y pantallas de la navegación
        NavHost(navController = navController, startDestination = "card_list") {
            composable("card_list") {
                //CardListScreen(navController = navController)
                CardListScreen(viewModel = viewModel)
            }
            composable("card_detail/{cardId}") { backStackEntry ->
                val cardId = backStackEntry.arguments?.getString("cardId")
                val card = viewModel.cards.find { it.id.toString() == cardId }
                card?.let {
                    CardDetailScreen(card = it)
                    //CardDetailScreen(navController = navController, card = it)
                }
            }
        }
    }
}
