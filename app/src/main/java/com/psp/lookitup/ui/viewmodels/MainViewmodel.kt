package com.psp.lookitup.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
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


    private var _requests = MutableLiveData<List<Request>>()
    val requests: LiveData<List<Request>> = _requests


    fun getUsers() {
        val requestUser: MutableList<User> = mutableListOf()
        val dbref = db.collection("users")
        dbref.get().addOnSuccessListener { users ->

            for (user in users.documents) {
                val req = Request()

            }

        }

    }


    fun getRequests() {

        val requestList: MutableList<Request> = mutableListOf()
        val docref = db.collection("requests")
        docref.get().addOnSuccessListener { requests ->

            for (request in requests.documents) {
                val req = Request()
                req.id = request.id
                req.Description = (request.data!!["Description"] as String?).toString()
                req.requestTitle = (request.data!!["requestTitle"] as String?).toString()
                req.roomLocation = (request.data!!["roomLocation"] as String?).toString()

                requestList.add(req)
            }
            _requests.value = requestList
        }
    }

    fun getFullRequest(id: String) {

    }


    fun addUser(user: User?) {
        user?.let {
            viewModelScope.launch(Dispatchers.IO) {
                userCollection.document(currentUser!!).set(it)
            }
        }
    }

    fun getUserById(id: String): Task<DocumentSnapshot> {
        return userCollection.document(id).get()
    }


    fun addRequest(request: Request) {
        db.collection("requests")
            .add(request)
    }
}