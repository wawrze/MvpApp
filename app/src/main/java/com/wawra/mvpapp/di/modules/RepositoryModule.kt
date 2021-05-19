package com.wawra.mvpapp.di.modules

import com.wawra.mvpapp.data.database.repositories.SampleLocalRepositoryImpl
import com.wawra.mvpapp.data.network.repositories.SampleNetworkRepositoryImpl
import com.wawra.mvpapp.domain.repositories.SampleLocalRepository
import com.wawra.mvpapp.domain.repositories.SampleNetworkRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSampleNetworkRepository(
        sampleNetworkRepository: SampleNetworkRepositoryImpl
    ): SampleNetworkRepository = sampleNetworkRepository

    @Provides
    @Singleton
    fun provideSampleLocalRepository(
        sampleLocalRepository: SampleLocalRepositoryImpl
    ): SampleLocalRepository = sampleLocalRepository
}
