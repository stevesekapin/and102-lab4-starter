package com.codepath.campgrounds

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CampgroundDao {

    @Query("SELECT * FROM campground_table")
    suspend fun getAll(): List<CampgroundEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(campgrounds: List<CampgroundEntity>)

    @Query("DELETE FROM campground_table")
    suspend fun deleteAll()
}