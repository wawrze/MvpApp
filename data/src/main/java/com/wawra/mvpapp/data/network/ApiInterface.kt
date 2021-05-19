package com.wawra.mvpapp.data.network

import com.wawra.mvpapp.data.network.models.SampleModelJson
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("/sample")
    fun sampleApiCall(): Single<SampleModelJson>
}
