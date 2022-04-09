package com.psp.lookitup.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.data.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(): ViewModel() {

    var verificationId = ""

    val db = Firebase.firestore

    private var _requests = MutableLiveData<ArrayList<Request>>()
    val requests: LiveData<ArrayList<Request>> = _requests

    fun getQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            val requestList : ArrayList<Request> = ArrayList()
            db.collection("requests")
                .get()
                .addOnSuccessListener { requests ->
                    requestList.clear()
                    for (request in requests){
                        val p = request.toObject(Request::class.java)
                        requestList.add(p)
                    }
                    for (i in requestList){
                        //Log.d("vm", i.requestTitle)
                    }
                }


            _requests.value = requestList
        }
    }

}
