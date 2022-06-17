package com.hari.notty.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hari.notty.core.database.dao.NoteDao
import com.hari.notty.core.database.model.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1,
    exportSchema = true,
)
abstract class NottyDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
