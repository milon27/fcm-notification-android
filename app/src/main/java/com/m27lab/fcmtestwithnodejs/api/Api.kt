package com.m27lab.fcmtestwithnodejs.api

import com.m27lab.fcmtestwithnodejs.model.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("/token")
    suspend fun setToken(
        @Field("name") name:String,
        @Field("token") token:String
    ): Response
}