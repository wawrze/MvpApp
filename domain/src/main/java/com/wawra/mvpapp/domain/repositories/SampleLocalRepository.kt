package com.wawra.mvpapp.domain.repositories

import com.wawra.mvpapp.domain.models.SampleModel
import io.reactivex.Single

interface SampleLocalRepository {
    fun insertSampleModel(sampleModel: SampleModel): Single<Boolean>
}