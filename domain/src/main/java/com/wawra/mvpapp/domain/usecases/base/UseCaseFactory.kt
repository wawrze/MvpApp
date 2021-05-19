package com.wawra.mvpapp.domain.usecases.base

import com.wawra.mvpapp.domain.repositories.SampleLocalRepository
import com.wawra.mvpapp.domain.repositories.SampleNetworkRepository
import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import com.wawra.mvpapp.domain.usecases.base.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class UseCaseFactory @Inject constructor(
    private val executionThread: Provider<ExecutionThread>,
    private val postExecutionThread: Provider<PostExecutionThread>,
    private val sampleLocalRepository: Provider<SampleLocalRepository>,
    private val sampleNetworkRepository: Provider<SampleNetworkRepository>
) {
    fun provideGetAndSaveSampleModelUseCase() = GetAndSaveSampleModelUseCase(
        sampleNetworkRepository.get(),
        sampleLocalRepository.get(),
        executionThread.get(),
        postExecutionThread.get()
    )
}
