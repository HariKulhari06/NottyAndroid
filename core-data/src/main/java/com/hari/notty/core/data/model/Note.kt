package com.hari.notty.core.data.model

import com.hari.notty.core.database.model.NoteEntity
import com.hari.notty.core.network.model.NetworkNote

fun NetworkNote.asEntity() = NoteEntity(
    id = id,
    title = title,
    description = description,
    createdAt = createdAt,
    updatedAt = updatedAt
)