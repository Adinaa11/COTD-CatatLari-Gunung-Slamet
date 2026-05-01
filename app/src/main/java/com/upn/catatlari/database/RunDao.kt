package com.upn.catatlari.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RunDao {
    @Insert
    suspend fun insert(run: RunEntity)
    @Update
    suspend fun update(run: RunEntity)
    @Delete
    suspend fun delete(run: RunEntity)
    @Query("SELECT * FROM run_table ORDER BY id DESC")
    fun getAllRuns():
            LiveData<List<RunEntity>>
}