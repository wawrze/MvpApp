package com.wawra.mvpapp.utils.executors

import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IOThread @Inject internal constructor() : ExecutionThread {

    override val scheduler: Scheduler
        get() = Schedulers.io()
}
