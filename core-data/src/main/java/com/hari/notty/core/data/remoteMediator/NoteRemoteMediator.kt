package com.hari.notty.core.data.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hari.notty.core.data.model.asEntity
import com.hari.notty.core.database.NottyDatabase
import com.hari.notty.core.database.model.NoteEntity
import com.hari.notty.core.database.model.NoteRemoteKeysEntity
import com.hari.notty.core.network.retrofit.NoteService

@OptIn(ExperimentalPagingApi::class)
class NoteRemoteMediator(
    private val nottyDatabase: NottyDatabase,
    private val noteService: NoteService
) : RemoteMediator<Int, NoteEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, NoteEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = noteService.getNotes(currentPage, state.config.pageSize)
            val endOfPaginationReached = response.data.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            nottyDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    nottyDatabase.noteDao().deleteAllNotes()
                    nottyDatabase.noteRemoteKeysDao().deleteAllRemoteKeys()
                }
                val keys = response.data.map { note ->
                    NoteRemoteKeysEntity(
                        id = note.id, prevPage = prevPage, nextPage = nextPage
                    )
                }
                nottyDatabase.noteRemoteKeysDao().addAllRemoteKeys(remoteKeys = keys)
                nottyDatabase.noteDao().upsertNotes(response.data.map { it.asEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)


            MediatorResult.Error(Throwable())
        } catch (e: Throwable) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, NoteEntity>
    ): NoteRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                nottyDatabase.noteRemoteKeysDao().getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, NoteEntity>
    ): NoteRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                nottyDatabase.noteRemoteKeysDao().getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, NoteEntity>
    ): NoteRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                nottyDatabase.noteRemoteKeysDao().getRemoteKeys(id = unsplashImage.id)
            }
    }
}