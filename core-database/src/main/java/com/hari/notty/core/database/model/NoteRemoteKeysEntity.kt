package com.hari.notty.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_remote_keys")
data class NoteRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)