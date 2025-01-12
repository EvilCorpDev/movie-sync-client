package com.androdighost77.client.model

import com.androdighost77.client.constants.ShowType

data class MovieInfo(
    val name: String,
    val id: String,
    val folder: String,
    val size: Long,
    val hashSum: String,
    val showType: ShowType,
    val owner: String,
)
