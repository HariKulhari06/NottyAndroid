package com.hari.notty.core.data.repository

import com.hari.notty.core.network.NoteNetworkDataSource
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteNetworkDataSource: NoteNetworkDataSource
) : NoteRepository {
    override suspend fun addNote() {
        noteNetworkDataSource.addNote()
    }
}