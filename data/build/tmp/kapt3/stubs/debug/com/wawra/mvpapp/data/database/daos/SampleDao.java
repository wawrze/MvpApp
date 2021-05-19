package com.wawra.mvpapp.data.database.daos;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/wawra/mvpapp/data/database/daos/SampleDao;", "", "insertSampleModel", "Lio/reactivex/Single;", "", "sampleModelEntity", "Lcom/wawra/mvpapp/data/database/models/SampleModelEntity;", "data_debug"})
public abstract interface SampleDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Insert()
    public abstract io.reactivex.Single<java.lang.Long> insertSampleModel(@org.jetbrains.annotations.NotNull()
    com.wawra.mvpapp.data.database.models.SampleModelEntity sampleModelEntity);
}