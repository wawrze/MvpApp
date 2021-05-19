package com.wawra.mvpapp.domain.repositories

import com.wawra.mvpapp.domain.models.SampleModel
import io.reactivex.Single

interface SampleNetworkRepository {
    fun getSampleModel(): Single<SampleModel>
}
