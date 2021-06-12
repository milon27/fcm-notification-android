package com.m27lab.fcmtestwithnodejs.repo

import com.m27lab.fcmtestwithnodejs.api.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object FcmRepo {
    suspend fun setToken(token:String)= flow {
        val str=RetrofitBuilder.api.setToken("milon",token)
        emit(str.msg)
    }.flowOn(Dispatchers.IO)
}