package com.hari.notty.core.data.repository

import androidx.paging.PagingData
import com.hari.notty.core.model.data.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotePagingData(): Flow<PagingData<Note>>

    suspend fun addNote(title: String, description: String)
}