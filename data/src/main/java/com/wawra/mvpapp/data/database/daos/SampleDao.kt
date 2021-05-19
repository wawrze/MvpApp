package com.wawra.mvpapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.wawra.mvpapp.data.database.models.SampleModelEntity
import io.reactivex.Single

@Dao
interface SampleDao {
    @Insert
    fun insertSampleModel(sampleModelEntity: SampleModelEntity): Single<Long>
}
