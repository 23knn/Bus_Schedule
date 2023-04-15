package com.example.busschedule.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.data.BusScheduleRepository

class BusScheduleViewModel(val repository: BusScheduleRepository): ViewModel() {

}