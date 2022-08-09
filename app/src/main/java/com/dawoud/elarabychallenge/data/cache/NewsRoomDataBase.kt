package com.dawoud.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.SourceEntityConverters


@Database(
    entities = [CountryNewsEntity::class , PopularNewsEntity::class], version = 1, exportSchema = false
)
@TypeConverters(SourceEntityConverters::class)
abstract class NewsRoomDataBase : RoomDatabase() {
    companion object {
        val DATABASE_NAME: String = "general_database"
    }
}