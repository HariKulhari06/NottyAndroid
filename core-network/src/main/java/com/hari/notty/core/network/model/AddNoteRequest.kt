package com.hari.notty.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AddNoteRequest(
    val title: String,
    val description: String,
){
    companion object {
        val SAMPLE = AddNoteRequest(
            title = "What is Notty?",
            description = "Notty is a not and reminder taking application."
        )
    }
}
