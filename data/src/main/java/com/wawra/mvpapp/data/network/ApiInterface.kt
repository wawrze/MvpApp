package com.wawra.mvpapp.data.network

import com.wawra.mvpapp.data.network.models.PostJson
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("/recruitment-task")
    fun getPosts(): Single<List<PostJson>>
}
