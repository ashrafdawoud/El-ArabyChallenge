package com.dawoud.elarabychallenge.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawoud.elarabychallenge.data.cache.entity.BookMarkNewsEntity

@Dao
interface BookMarkNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BookMarkNewsEntity): Long

    @Query("SELECT * From BookMarkNewsTable")
    suspend fun getAllNews(): List<BookMarkNewsEntity>

    @Query("SELECT EXISTS(SELECT * FROM BookMarkNewsTable where url=:url and title=:title)")
    suspend fun checkIfNewExsist(url:String?,title:String?): Boolean

    @Query("DELETE From BookMarkNewsTable where url=:url AND title=:title")
    suspend fun delete_Raw(url:String?,title:String?):Int
}