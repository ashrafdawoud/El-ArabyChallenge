package com.dawoud.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dawoud.elarabychallenge.data.cache.entity.NewsEntity


@Database(
    entities = [NewsEntity::class], version = 1, exportSchema = false
)
abstract class NewsRoomDataBase : RoomDatabase() {
    companion object {
        val DATABASE_NAME: String = "general_database"
    }
}