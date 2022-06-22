package com.hari.notty.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNote(
    val id: String,
    val title: String,
    val description: String,
    val createdAt: Long,
    @SerialName("updatedAt")
    val updatedAt: Long? = null
) {

}
