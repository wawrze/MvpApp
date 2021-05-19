package com.wawra.mvpapp.data.database.repositories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/wawra/mvpapp/data/database/repositories/SampleLocalRepositoryImpl;", "Lcom/wawra/mvpapp/domain/repositories/SampleLocalRepository;", "sampleDao", "Lcom/wawra/mvpapp/data/database/daos/SampleDao;", "(Lcom/wawra/mvpapp/data/database/daos/SampleDao;)V", "insertSampleModel", "Lio/reactivex/Single;", "", "sampleModel", "Lcom/wawra/mvpapp/domain/models/SampleModel;", "data_debug"})
public final class SampleLocalRepositoryImpl implements com.wawra.mvpapp.domain.repositories.SampleLocalRepository {
    private final com.wawra.mvpapp.data.database.daos.SampleDao sampleDao = null;
    
    @javax.inject.Inject()
    public SampleLocalRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.wawra.mvpapp.data.database.daos.SampleDao sampleDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<java.lang.Boolean> insertSampleModel(@org.jetbrains.annotations.NotNull()
    com.wawra.mvpapp.domain.models.SampleModel sampleModel) {
        return null;
    }
}