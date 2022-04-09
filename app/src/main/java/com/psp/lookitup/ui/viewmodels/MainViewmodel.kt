package com.psp.lookitup.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.data.Request
import com.psp.lookitup.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(): ViewModel() {
    val db = Firebase.firestore
    fun searchByLocation(location:String){
        Log.d("ViewModel", location)
    }

    fun addRequest(request: Request){
        Log.d("Request", request.id.toString())
    }

    fun requestDetails(id:Int){
        Log.d("ID", id.toString())
    }

    fun addUserDetails(user: User){
        db.collection("users")
            .add(user)
    }
}
