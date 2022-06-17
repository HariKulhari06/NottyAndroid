package com.hari.notty.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNottyDatabase(
        @ApplicationContext context: Context,
    ): NottyDatabase = Room.databaseBuilder(
        context,
        NottyDatabase::class.java,
        "notty-database"
    ).build()
}
