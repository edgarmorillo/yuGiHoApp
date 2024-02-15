package com.app.myappyugiho.presentation.carddetail

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.app.myappyugiho.data.model.Card
import com.app.myappyugiho.R
import androidx.navigation.NavController
import coil.request.ImageRequest

// fun CardDetailScreen(navController: NavController, card: Card)

@Composable
fun CardDetailScreen(card: Card) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            CardImage(url = card.imageUrl)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = card.name,
                fontSize = 24.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "ArcheType: ${card.archetype}",
                fontSize = 18.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${card.desc}",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Type: ${card.type}",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Attribute: ${card.attribute}",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "ATK: ${card.atk}",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Agregar más detalles de la carta según sea necesario
        }
    }
}

@Composable
fun CardImage(url: String) {
    val painter: Painter? = if (url.isNotEmpty()) {
        GlideImage(context = LocalContext.current,imageUrl = url, fallback = R.drawable.ic_launcher_foreground)
    } else {
        painterResource(id = R.drawable.ic_launcher_foreground)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    )
    {
        if (painter != null) {
            Image(
                painter = painter,
                contentDescription = "Card Image",
                modifier = Modifier
                    .size(200.dp)
                //.align(Alignment.CenterHorizontally)
            )
        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun GlideImage(context: Context, imageUrl: String, fallback: Int): Painter? {
    val imagePainter = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data(imageUrl)
            .error(fallback)
            .fallback(fallback)
            .placeholder(fallback)
            .build()
    )

    return when (val painterState = imagePainter.state) {
        is AsyncImagePainter.State.Success -> {
            painterState.painter
        }
        else -> {
            painterResource(id = fallback)
        }
    }
}
