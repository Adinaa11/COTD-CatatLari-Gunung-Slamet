package com.upn.catatlari.database

class RunRepository(private val runDao: RunDao) {
    val allRuns = runDao.getAllRuns()
    suspend fun insert(run: RunEntity) {
        runDao.insert(run)
    }
    suspend fun update(run: RunEntity) {
        runDao.update(run)
    }
    suspend fun delete(run: RunEntity) {
        runDao.delete(run)
    }
}