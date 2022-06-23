package com.hari.notty.core.data.repository

import androidx.paging.*
import com.hari.notty.core.data.remoteMediator.NoteRemoteMediator
import com.hari.notty.core.database.NottyDatabase
import com.hari.notty.core.database.model.NoteEntity
import com.hari.notty.core.database.model.toExternalNote
import com.hari.notty.core.model.data.Note
import com.hari.notty.core.network.NoteNetworkDataSource
import com.hari.notty.core.network.model.AddNoteRequest
import com.hari.notty.core.network.retrofit.NoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class NoteRepositoryImpl @Inject constructor(
    private val noteService: NoteService,
    private val noteNetworkDataSource: NoteNetworkDataSource,
    private val nottyDatabase: NottyDatabase
) : NoteRepository {

    override fun getNotePagingData(): Flow<PagingData<Note>> {
        val pagingSourceFactory = { nottyDatabase.noteDao().getNoteEntityPagingSource() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = NoteRemoteMediator(
                nottyDatabase = nottyDatabase,
                noteService = noteService
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData: PagingData<NoteEntity> ->
                pagingData.map { noteEntity: NoteEntity ->
                    noteEntity.toExternalNote()
                }
            }
    }

    override suspend fun addNote(title: String, description: String) {
        noteNetworkDataSource.addNote(
            AddNoteRequest(
                title = title,
                description = description
            )
        )
    }

    companion object {
        const val ITEMS_PER_PAGE = 10
    }
}