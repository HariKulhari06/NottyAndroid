package com.hari.notty.core.network

import com.hari.notty.core.network.model.AddNoteRequest

interface NoteNetworkDataSource {
    suspend fun addNote(addNoteRequest: AddNoteRequest)
}