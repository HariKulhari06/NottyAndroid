package com.hari.notty.core.network

import com.hari.notty.core.network.model.AddNoteRequest
import com.hari.notty.core.network.retrofit.NoteService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteNetworkDataSourceImpl @Inject constructor(
    private val noteService: NoteService
) : NoteNetworkDataSource {
    override suspend fun addNote(addNoteRequest: AddNoteRequest) {
        noteService.addNote(addNoteRequest)
    }
}