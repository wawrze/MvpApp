package com.wawra.mvpapp.domain.usecases.base.executor

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}
