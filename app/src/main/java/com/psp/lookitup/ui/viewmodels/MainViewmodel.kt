package com.psp.lookitup.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.data.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor() : ViewModel() {

    var verificationId = ""

    val db = Firebase.firestore

    private var _requests = MutableLiveData<List<Request>>()
    val requests: LiveData<List<Request>> = _requests

    fun getRequests() {

        val requestList: MutableList<Request> = mutableListOf()
        val docref = db.collection("requests")
        docref.get().addOnSuccessListener { requests ->

            for (request in requests.documents) {
                val req = Request()
                req.Description = (request.data!!["Description"] as String?).toString()
                req.requestTitle = (request.data!!["requestTitle"] as String?).toString()
                req.roomLocation = (request.data!!["roomLocation"] as String?).toString()

                requestList.add(req)
            }
            _requests.value = requestList
        }
    }

    fun getFullRequest(id: String){
        TODO("do this")
    }

    fun addRequest(request: Request){
        db.collection("requests")
            .add(request)
    }

}
