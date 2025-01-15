package com.androdighost77.client.model

import com.androdighost77.client.constants.ShowType

data class UpdateEvent(
    val fileName: String,
    val id: String,
    val type: ShowType,
    val checksum: String,
    val username: String
)
