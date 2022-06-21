package com.hari.notty.core.data.di

import com.hari.notty.core.data.repository.NoteRepository
import com.hari.notty.core.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}