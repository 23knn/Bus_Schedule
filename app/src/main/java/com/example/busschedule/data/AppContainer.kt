package com.example.busschedule.data

import android.content.Context

// exposes the dependencies to the app. make sure to initialize it after onCreate of the app
interface AppContainer {
    val busScheduleRepository: BusScheduleRepository
}

class DefaultAppContainer(context: Context): AppContainer {
    override val busScheduleRepository: BusScheduleRepository by lazy {
        OfflineBusScheduleRepository(schedulesDatabaseCompanion.getDatabaseInstance(context = context).BusScheduleDao())
    }
}