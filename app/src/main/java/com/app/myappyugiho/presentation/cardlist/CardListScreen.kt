package com.app.myappyugiho.presentation.cardlist

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.app.myappyugiho.R
import com.app.myappyugiho.data.model.Card
import com.bumptech.glide.Glide

//import dev.chrisbanes.accompanist.coil.rememberCoilPainter

// fun CardListScreen(navController: NavController, viewModel: CardListViewModel = viewModel())

@Composable
fun CardListScreen(viewModel: CardListViewModel = viewModel()) {
    val archetypeQuery = remember { mutableStateOf(TextFieldValue()) }
    val cards = viewModel.cards

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = archetypeQuery.value,
            onValueChange = { archetypeQuery.value = it },
            label = { Text(text = "Search by archetype") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cards.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Color.Red)
        } else {
            CardList(cards = cards, archetypeQuery = archetypeQuery.value.text)
        }
    }
}

@Composable
fun CardList(cards: List<Card>, archetypeQuery: String) {
    LazyColumn {
        items(cards.filter { it.archetype.contains(archetypeQuery, ignoreCase = true) }) { card ->
            CardItem(card = card)
        }
    }
}

@Composable
fun CardItem(card: Card) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            CardImage(url = card.imageUrl)
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = card.name, fontSize = 18.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "ArcheType: ${card.archetype}", fontSize = 14.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Description: ${card.desc}", fontSize = 14.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Type: ${card.type}", fontSize = 14.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Attribute: ${card.attribute}", fontSize = 14.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "ATK: ${card.atk}", fontSize = 14.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(4.dp))
                // Agregar más detalles de la carta según sea necesario
            }
        }
    }
}

@Composable
fun CardImage(url: String) {
    val painter = // Agregar efecto de transición
        rememberAsyncImagePainter(ImageRequest.Builder // Imagen de carga
        // Imagen en caso de error
            (LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
            crossfade(true) // Agregar efecto de transición
            placeholder(R.drawable.ic_launcher_foreground) // Imagen de carga
            error(R.drawable.ic_launcher_foreground) // Imagen en caso de error
        }).build()
        )

    Image(
        painter = painter,
        contentDescription = "Card Image",
        modifier = Modifier.size(100.dp)
    )
}



