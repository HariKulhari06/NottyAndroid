package com.hari.notty.core.model.data

data class Note(
    val id: String,
    val title: String,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long?
)
