package com.upn.catatlari.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.upn.catatlari.database.RunDatabase
import com.upn.catatlari.database.RunEntity
import com.upn.catatlari.database.RunRepository
import kotlinx.coroutines.launch

class RunViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RunRepository
    val allRuns: androidx.lifecycle.LiveData<List<RunEntity>>

    init {
        val dao =
            RunDatabase.getDatabase(application).runDao()
        repository = RunRepository(dao)
        allRuns = repository.allRuns
    }
    fun insert(run: RunEntity) = viewModelScope.launch {
            repository.insert(run)
        }
    fun update(run: RunEntity) = viewModelScope.launch {
        repository.update(run)
    }
    fun delete(run: RunEntity) = viewModelScope.launch {
        repository.delete(run)
    }

}