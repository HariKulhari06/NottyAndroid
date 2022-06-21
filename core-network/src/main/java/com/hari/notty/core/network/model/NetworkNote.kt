package com.hari.notty.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkNote(
    val title:String,
    val description:String
){
    companion object{
        val SAMPLE  = NetworkNote(
            title = "What is Notty?",
            description = "Notty is a not and reminder taking application."
        )
    }
}
