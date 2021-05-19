package com.wawra.mvpapp.domain.usecases.base

import com.wawra.mvpapp.domain.repositories.SampleLocalRepository
import com.wawra.mvpapp.domain.repositories.SampleNetworkRepository
import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import com.wawra.mvpapp.domain.usecases.base.executor.PostExecutionThread
import io.reactivex.Single

class GetAndSaveSampleModelUseCase(
    private val sampleNetworkRepository: SampleNetworkRepository,
    private val sampleLocalRepository: SampleLocalRepository,
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean>(executionThread, postExecutionThread) {

    override fun createSingle(): Single<Boolean> = sampleNetworkRepository.getSampleModel()
        .flatMap { sampleLocalRepository.insertSampleModel(it) }
}
