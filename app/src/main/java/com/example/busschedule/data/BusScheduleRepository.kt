package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow


interface BusScheduleRepository {
    suspend fun insertBusSchedule(schedule: BusSchedule): Unit
    suspend fun updateBusSchedule(schedule: BusSchedule): Unit
    suspend fun deleteBusSchedule(schedule: BusSchedule): Unit
    fun getAllBusSchedules(): Flow<List<BusSchedule>>
    fun getBusSchedule(id: Int): Flow<BusSchedule>
    /**
     * when returning  Flow object, we don't need to add suspend. this is because a flow is an observable
     * and it prevents us from having to fetch data when data changes. when data changes, the flow automatically updates
     * us with the new data.
    **/
}

// the dao comes in as private because only the repository can use it
class OfflineBusScheduleRepository(private val busScheduleDao: BusScheduleDAO): BusScheduleRepository {
    override suspend fun insertBusSchedule(schedule: BusSchedule) = busScheduleDao.insertSchedule(schedule = schedule)

    override suspend fun updateBusSchedule(schedule: BusSchedule) = busScheduleDao.updateSchedule(schedule = schedule)

    override suspend fun deleteBusSchedule(schedule: BusSchedule) = busScheduleDao.deleteSchedule(schedule = schedule)

    override fun getAllBusSchedules(): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    override fun getBusSchedule(id: Int): Flow<BusSchedule> = busScheduleDao.getSchedule(id = id)

}