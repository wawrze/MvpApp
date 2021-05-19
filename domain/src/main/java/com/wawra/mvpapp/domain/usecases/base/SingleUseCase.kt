package com.wawra.mvpapp.domain.usecases.base

import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import com.wawra.mvpapp.domain.usecases.base.executor.PostExecutionThread
import io.reactivex.Single

abstract class SingleUseCase<T>
protected constructor(
    private val executionThread: ExecutionThread,
    private val postExecutionThread: PostExecutionThread
) {

    fun execute(): Single<T> = createSingle()
        .subscribeOn(executionThread.scheduler)
        .observeOn(postExecutionThread.scheduler)

    protected abstract fun createSingle(): Single<T>
}
