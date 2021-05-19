package com.wawra.mvpapp.di.modules

import android.content.Context
import androidx.room.Room
import com.wawra.mvpapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "mvp_app.db"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideSimpleDao(appDatabase: AppDatabase) = appDatabase.sampleDao()
}
