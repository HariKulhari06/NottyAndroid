package com.hari.notty.core.data.repository

import com.hari.notty.core.network.NoteNetworkDataSource
import com.hari.notty.core.network.model.AddNoteRequest
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteNetworkDataSource: NoteNetworkDataSource
) : NoteRepository {
    override suspend fun addNote(title: String, description: String) {
        noteNetworkDataSource.addNote(
            AddNoteRequest(
                title  = title,
                description = description
            )
        )
    }
}