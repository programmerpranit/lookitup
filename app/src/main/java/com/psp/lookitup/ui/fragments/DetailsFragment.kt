package com.psp.lookitup.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R
import com.psp.lookitup.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    val TAG = "Details Fragment"


    lateinit var sp1t: TextView


    lateinit var sp2t: TextView


    lateinit var sp3t: TextView

    val dbref = Firebase.firestore

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = hashMapOf(
            "name" to "Prathamesh Karambelkar",
            "emailId" to "pkarambelkar.2002@gmail.com",
            "DOB" to "19",
            "gender" to "Male",
            "Status" to "Looking for room",
            "occupation" to "student",
            "location" to "Ambernath"
        )
// Add a new document with a generated ID
        dbref.collection("User")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }
}