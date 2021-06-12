package com.m27lab.fcmtestwithnodejs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m27lab.fcmtestwithnodejs.repo.FcmRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FcmViewModel:ViewModel() {
    fun setToken(token:String): LiveData<String> {
        val livedataRes=MutableLiveData<String>()
        viewModelScope.launch(Dispatchers.IO) {
            FcmRepo.setToken(token).catch {e->
                livedataRes.postValue(e.message)
            }.collect {data->
                livedataRes.postValue(data)
            }
        }
        return livedataRes
    }
}