package com.app.myappyugiho.data.model

data class Card(
    val id: Int,
    val name: String,
    val archetype: String,
    val imageUrlSmall: String,
    val imageUrl: String,
    val desc: String,
    val type: String,
    val attribute: String?,
    val atk: Int?,
    //
)