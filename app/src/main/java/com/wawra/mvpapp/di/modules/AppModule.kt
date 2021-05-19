package com.wawra.mvpapp.di.modules

import android.content.Context
import com.wawra.mvpapp.MvpApp
import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import com.wawra.mvpapp.domain.usecases.base.executor.PostExecutionThread
import com.wawra.mvpapp.utils.executors.IOThread
import com.wawra.mvpapp.utils.executors.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: MvpApp): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideExecutionThread(ioThread: IOThread): ExecutionThread = ioThread

    @Provides
    @Singleton
    fun providePostExecutionThread(mainThread: UIThread): PostExecutionThread = mainThread
}
