package com.dawoud.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.data.cache.dao.PopularNewsDao
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.SourceEntityConverters


@Database(
    entities = [CountryNewsEntity::class , PopularNewsEntity::class], version = 2, exportSchema = false
)
@TypeConverters(SourceEntityConverters::class)
abstract class NewsRoomDataBase : RoomDatabase() {
    abstract fun countryNewsDao():CountryNewsDao
    abstract fun popularNewsDao():PopularNewsDao

    companion object {
        val DATABASE_NAME: String = "general_database"
    }
}