package com.dawoud.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity

@Dao
interface PopularNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PopularNewsEntity): Long

    @Query("SELECT * From PopularNewsTable")
    suspend fun getAllNews(): List<PopularNewsEntity>

    @Query("SELECT * From PopularNewsTable WHERE id=:id LIMIT 1")
    suspend fun getNews(id:Int): PopularNewsEntity

    @Query("DELETE From PopularNewsTable")
    suspend fun delete_table()
}