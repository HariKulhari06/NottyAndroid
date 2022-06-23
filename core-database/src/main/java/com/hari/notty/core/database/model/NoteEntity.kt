package com.hari.notty.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hari.notty.core.model.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long? = null
)


fun NoteEntity.toExternalNote() = Note(
    id = id,
    title = title,
    description = description,
    createdAt = createdAt,
    updatedAt = updatedAt
)