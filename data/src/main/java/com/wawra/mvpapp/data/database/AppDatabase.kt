package com.wawra.mvpapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wawra.mvpapp.data.database.daos.PostDao
import com.wawra.mvpapp.data.database.models.PostEntity

@Database(
    entities = [
        PostEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}