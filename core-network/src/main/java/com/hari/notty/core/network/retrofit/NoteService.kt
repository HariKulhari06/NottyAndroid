package com.hari.notty.core.network.retrofit

import com.hari.notty.core.network.model.NetworkNote
import retrofit2.http.*

interface NoteService {
    @GET("v1/api/note")
    suspend fun getNotes()

    @GET("v1/api/note/{note_id}")
    suspend fun getNote(@Query("note_id") noteId: String)

    @POST("v1/api/note")
    suspend fun addNote(@Body networkNote: NetworkNote = NetworkNote.SAMPLE): String

    @PUT("v1/api/note")
    suspend fun updateNote()

    @DELETE("v1/api/note/{note_id}")
    suspend fun deleteNote(@Query("note_id") noteId: String)
}