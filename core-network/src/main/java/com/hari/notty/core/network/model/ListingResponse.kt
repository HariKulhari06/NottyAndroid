package com.hari.notty.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ListingResponse<T>(
    val data: T,

    val totalPage: Long,

    val totalResult: Long,
)
