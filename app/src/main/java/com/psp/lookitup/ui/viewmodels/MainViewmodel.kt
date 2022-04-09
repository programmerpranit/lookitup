package com.psp.lookitup.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.psp.lookitup.data.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(): ViewModel() {

    fun searchByLocation(location:String){
        Log.d("ViewModel", location)
    }

    fun addRequest(request: Request){
        Log.d("Request", request.title)
    }

    fun requestDetails(id:Int){
        Log.d("ID", id.toString())
    }


}
