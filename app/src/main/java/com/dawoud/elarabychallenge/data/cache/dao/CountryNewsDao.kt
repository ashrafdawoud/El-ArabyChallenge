package com.dawoud.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity

@Dao
interface CountryNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CountryNewsEntity): Long

    @Query("SELECT * From CountryNewsTable")
    suspend fun getAllNews(): List<CountryNewsEntity>

    @Query("SELECT * From CountryNewsTable WHERE id=:id LIMIT 1")
    suspend fun getNews(id:Int): CountryNewsEntity

    @Query("DELETE From CountryNewsTable")
    suspend fun delete_table()
}