package com.example.platziconf.viewmodel

import com.example.platziconf.model.Conference
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.platziconf.network.Callback
import com.example.platziconf.network.FirestoreService
import java.lang.Exception

//comunica la base de datos en firebase con nuestros activities
class ScheduleViewModel:ViewModel(){
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }
    fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }
            override fun onFail(exeption: Exception) {
                processFinished()

            }
        })
    }
    fun processFinished(){
        isLoading.value = true
    }
}
