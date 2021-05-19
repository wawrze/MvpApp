package com.wawra.mvpapp.data.network.repositories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/wawra/mvpapp/data/network/repositories/SampleNetworkRepositoryImpl;", "Lcom/wawra/mvpapp/domain/repositories/SampleNetworkRepository;", "apiInterface", "Lcom/wawra/mvpapp/data/network/ApiInterface;", "(Lcom/wawra/mvpapp/data/network/ApiInterface;)V", "getSampleModel", "Lio/reactivex/Single;", "Lcom/wawra/mvpapp/domain/models/SampleModel;", "data_debug"})
public final class SampleNetworkRepositoryImpl implements com.wawra.mvpapp.domain.repositories.SampleNetworkRepository {
    private final com.wawra.mvpapp.data.network.ApiInterface apiInterface = null;
    
    @javax.inject.Inject()
    public SampleNetworkRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.wawra.mvpapp.data.network.ApiInterface apiInterface) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.wawra.mvpapp.domain.models.SampleModel> getSampleModel() {
        return null;
    }
}