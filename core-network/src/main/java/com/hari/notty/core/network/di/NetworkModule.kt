package com.hari.notty.core.network.di

import com.hari.notty.core.network.BuildConfig
import com.hari.notty.core.network.NoteNetworkDataSource
import com.hari.notty.core.network.NoteNetworkDataSourceImpl
import com.hari.notty.core.network.retrofit.NoteService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindNoteNetworkDataSource(impl: NoteNetworkDataSourceImpl): NoteNetworkDataSource


    companion object {
        @Singleton
        @Provides
        fun provideJson(): Json {
            return Json {
                ignoreUnknownKeys = true
            }
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        }


        @Singleton
        @Provides
        fun provideRetrofit(
            json: Json,
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BACKEND_DEV_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            // TODO: Decide logging logic
                            HttpLoggingInterceptor().apply {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            }
                        )
                        .build()
                )
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        }


        @Provides
        fun provideNoteService(retrofit: Retrofit): NoteService {
            return retrofit.create(NoteService::class.java)
        }
    }
}