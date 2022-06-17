package com.hari.notty.core.database

import com.hari.notty.core.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesNoteDao(
        database: NottyDatabase,
    ): NoteDao = database.noteDao()

}
