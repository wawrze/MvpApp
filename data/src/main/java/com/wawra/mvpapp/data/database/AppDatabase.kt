package com.wawra.mvpapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wawra.mvpapp.data.database.daos.SampleDao
import com.wawra.mvpapp.data.database.models.SampleModelEntity

@Database(
    entities = [
        SampleModelEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}