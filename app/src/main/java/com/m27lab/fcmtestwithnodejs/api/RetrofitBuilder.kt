package com.m27lab.fcmtestwithnodejs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val BASE_URL="http://192.168.56.1:2727"
    private val builder=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val api= builder.create(Api::class.java)
}