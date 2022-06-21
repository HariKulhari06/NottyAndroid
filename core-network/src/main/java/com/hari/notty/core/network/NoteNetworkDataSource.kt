package com.hari.notty.core.network

interface NoteNetworkDataSource {
    suspend fun addNote()
}