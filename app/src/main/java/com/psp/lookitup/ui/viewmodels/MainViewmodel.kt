package com.psp.lookitup.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.psp.lookitup.data.Request
import com.psp.lookitup.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor() : ViewModel() {

    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser?.uid
    val db = FirebaseFirestore.getInstance()
    val userCollection = db.collection("users")
    var verificationId = ""

    val TAG = "MainVM"

    private var _requestDetails = MutableLiveData<Request>()
    val requestDetails: LiveData<Request> = _requestDetails


    private var _userDetails = MutableLiveData<User>()
    val userDetails :LiveData<User> = _userDetails


    private var _requests = MutableLiveData<List<Request>>()
    val requests: LiveData<List<Request>> = _requests


    fun getUsers() {
        val docref = db.collection("users").document("User")
        docref.get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject(User::class.java)
        }
    }

    fun getRequests(search: String?) {

        Log.d(TAG, search?: "Search is Empty")

        val docref = if (search.isNullOrBlank() || search.isNullOrEmpty()){
            db.collection("requests")
        } else {
            Log.d(TAG, "In Else")
            db.collection("requests").orderBy("roomLocation") .startAt(search).endAt(search+"\uf8ff")
        }

        val requestList: MutableList<Request> = mutableListOf()

        docref.get().addOnSuccessListener { requests ->

            for (request in requests.documents) {
                val req = Request()
                req.id = request.id
                req.Description = (request.data!!["Description"] as String?).toString()
                req.requestTitle = (request.data!!["requestTitle"] as String?).toString()
                req.roomLocation = (request.data!!["roomLocation"] as String?).toString()

                Log.d(TAG, req.roomLocation)
                requestList.add(req)
            }
            _requests.value = requestList
        }
    }

    fun getFullRequest(id: String) {
        val document = db.collection("requests").document(id)
        document.get()
            .addOnSuccessListener { request ->
                val req = Request()
                req.id = request.id
                req.Description = (request.data!!["Description"] as String?).toString()
                req.requestTitle = (request.data!!["requestTitle"] as String?).toString()
                req.roomLocation = (request.data!!["roomLocation"] as String?).toString()
                _requestDetails.value = req
            }
            .addOnFailureListener {
                Log.d(TAG, "data fetch failed")
            }
    }


    fun addUser(user: User?) {
        user?.let {
            viewModelScope.launch(Dispatchers.IO) {
                userCollection.document(currentUser!!).set(it)
            }
        }
    }

//    fun getUserEmail(id: String):String {
//         userCollection.document(id).get().addOnSuccessListener {
//            return@addOnSuccessListener it.data?.get("emailId")
//
//         }
//    }



    fun searchRequests(search: String){

    }


    fun addRequest(request: Request) {
        db.collection("requests")
            .add(request)
    }
}