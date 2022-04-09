package com.psp.lookitup.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R
import com.psp.lookitup.databinding.FragmentLoginBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel

class LoginFragment : Fragment(R.layout.fragment_login) {

    val TAG = "Login"

    private val viewmodel: MainViewmodel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val db = Firebase.firestore
//
//        val user = hashMapOf(
//            "name" to "Ada",
//            "emailId" to "psp@gmail.com"
//        )
//
//        view.findViewById<Button>(R.id.adduser).setOnClickListener{
//            db.collection("users")
//                .add(user)
//                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}