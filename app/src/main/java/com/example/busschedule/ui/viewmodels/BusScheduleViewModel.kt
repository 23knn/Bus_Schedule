package com.example.busschedule.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusScheduleRepository
import kotlinx.coroutines.launch

class BusScheduleViewModel(val repository: BusScheduleRepository): ViewModel() {
    var homeUIState = viewModelScope.launch {
            repository.getAllBusSchedules()
        }
    object viewModelProvider {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as BusScheduleApplication) // get the app
                val repo = app.container.busScheduleRepository// get the repo. our VM needs it
                BusScheduleViewModel(repository = repo)
            }
        }
    }
}