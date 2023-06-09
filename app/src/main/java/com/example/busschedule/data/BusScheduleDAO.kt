package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDAO {
    // this is the skeleton of the methods used to interact with the database. Room generates an
    // .. implementation of it at compile time

    // can also use @Upsert if we want to replace when a value already exists in the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSchedule(schedule: BusSchedule)

    @Update
    suspend fun updateSchedule(schedule: BusSchedule)

    @Delete
    suspend fun deleteSchedule(schedule: BusSchedule)

    @Query("SELECT * FROM Schedule")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM Schedule where stop_name = :stopName")
    fun getScheduleByStopName(stopName: String): Flow<List<BusSchedule>>
}