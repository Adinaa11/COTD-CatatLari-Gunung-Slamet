package com.upn.catatlari.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "run_table")
data class RunEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val runDate: String,
    val runDistance: Int,
    val runDuration: Int
)
