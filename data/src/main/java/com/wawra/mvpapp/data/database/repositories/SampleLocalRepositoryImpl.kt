package com.wawra.mvpapp.data.database.repositories

import com.wawra.mvpapp.data.database.daos.SampleDao
import com.wawra.mvpapp.data.database.models.SampleModelEntity
import com.wawra.mvpapp.domain.models.SampleModel
import com.wawra.mvpapp.domain.repositories.SampleLocalRepository
import io.reactivex.Single
import javax.inject.Inject

class SampleLocalRepositoryImpl @Inject constructor(
    private val sampleDao: SampleDao
) : SampleLocalRepository {
    override fun insertSampleModel(sampleModel: SampleModel): Single<Boolean> = sampleDao
        .insertSampleModel(SampleModelEntity(sampleModel))
        .map { true }
        .onErrorReturn { false }
}
