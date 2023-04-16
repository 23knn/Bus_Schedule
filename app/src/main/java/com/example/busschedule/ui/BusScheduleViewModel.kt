/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.BusScheduleRepository
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val repository: BusScheduleRepository): ViewModel() {

    // Get example bus schedule from Room DB
    fun getFullSchedule(): Flow<List<BusSchedule>> = repository.getAllBusSchedules()
    // we don't put the above in a coroutine scope since we get the flow and flow doesn't come from a suspend function

    // Get example bus schedule by stop
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = repository.getScheduleByStopName(stopName = stopName)

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as BusScheduleApplication)// get app
                val repo = app.container.busScheduleRepository // get repository
                BusScheduleViewModel(repository = repo) // inject
            }
        }
    }
}


