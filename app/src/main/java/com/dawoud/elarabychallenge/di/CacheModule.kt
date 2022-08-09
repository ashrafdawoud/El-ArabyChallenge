package com.dawoud.androidengineerchallenge.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dawoud.data.cache.NewsRoomDataBase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): NewsRoomDataBase {
        return Room.databaseBuilder(context, NewsRoomDataBase::class.java, NewsRoomDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}