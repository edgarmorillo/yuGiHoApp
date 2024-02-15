package com.app.myappyugiho.data.network.response

import com.app.myappyugiho.data.model.BanListItem
import com.app.myappyugiho.data.model.Card
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardResponse(
    @Json(name = "data")
    val cards: List<Card>

)

@JsonClass(generateAdapter = true)
data class BanListResponse(
    @Json(name = "banlist_info")
    val banListItems: List<BanListItem>
)