package com.dawoud.androidengineerchallenge.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dawoud.data.cache.NewsRoomDataBase
import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.data.cache.dao.PopularNewsDao
import com.dawoud.elarabychallenge.data.cache.dao.BookMarkNewsDao

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
    @Singleton
    @Provides
    fun provideCountryNewsDAO(roomDatabase: NewsRoomDataBase): CountryNewsDao {
        return roomDatabase.countryNewsDao()
    }
    @Singleton
    @Provides
    fun providePopularNewsDAO(roomDatabase: NewsRoomDataBase): PopularNewsDao {
        return roomDatabase.popularNewsDao()
    }
    @Singleton
    @Provides
    fun provideBookMarkNewsDAO(roomDatabase: NewsRoomDataBase): BookMarkNewsDao {
        return roomDatabase.bookmarkDao()
    }
}