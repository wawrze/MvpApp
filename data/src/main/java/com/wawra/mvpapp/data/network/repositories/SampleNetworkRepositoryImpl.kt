package com.wawra.mvpapp.data.network.repositories

import com.wawra.mvpapp.data.network.ApiInterface
import com.wawra.mvpapp.domain.models.SampleModel
import com.wawra.mvpapp.domain.repositories.SampleNetworkRepository
import io.reactivex.Single
import javax.inject.Inject

class SampleNetworkRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : SampleNetworkRepository {

    override fun getSampleModel(): Single<SampleModel> = apiInterface.sampleApiCall()
        .map { it.toDomain() }
}