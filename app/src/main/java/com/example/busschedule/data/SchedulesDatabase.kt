package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// list of entities in the DB, version changes every time the DAOs are changed. export schema false means no storing backups of older versions
@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class SchedulesDatabase: RoomDatabase() {
    /**this class is supposed to be a holder of a database instance rather than the database itself.
     * it is abstract because we want only one instance of the database since creating new ones is expensive.
     * later, we will add @Volatile annotation somewhere to help reduce the overhead as well.
     *
     * this class also holds all the DAOs related to the database in question. This allows for it to
     * inject those DAOs anywhere they are needed. Similar to Service being injected in previous projects.
     * The DAO is actually just a service
     *
     * this class has a function that when called, returns the instance of the db. if there is already one,
     * it is returned. otherwise, one is made and assigned to the instance variable and returned
     *
     * We need a companion object to do so. why? well, we cant instantiate this class but we need access inside
     * it and the only things that lets us do so are objects
     * */

    abstract fun BusScheduleDao(): BusScheduleDAO


}

object schedulesDatabaseCompanion{ //
    @Volatile // make the db stored in main memory. no caching so the threads get since source of truth
    private var Instance: SchedulesDatabase? = null

    fun getDatabaseInstance(context: Context): SchedulesDatabase {
        return Instance ?: synchronized(this) {
            Room.databaseBuilder(context, SchedulesDatabase::class.java, "schedules_database")
                .build()
                .also { Instance = it }
        }
    }
}