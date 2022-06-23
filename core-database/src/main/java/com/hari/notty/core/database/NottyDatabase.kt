package com.hari.notty.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hari.notty.core.database.dao.NoteDao
import com.hari.notty.core.database.dao.NoteRemoteKeysDao
import com.hari.notty.core.database.model.NoteEntity
import com.hari.notty.core.database.model.NoteRemoteKeysEntity

@Database(
    entities = [
        NoteEntity::class,
        NoteRemoteKeysEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
public abstract class NottyDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun noteRemoteKeysDao(): NoteRemoteKeysDao
}
