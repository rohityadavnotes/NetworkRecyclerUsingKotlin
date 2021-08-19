package com.using.kotlin.data.remote

import com.using.kotlin.model.Data
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(RemoteConfiguration.VERSION)
    fun getAndroidVersion(): Observable<Response<Data>>
}