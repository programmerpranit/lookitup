package com.psp.lookitup.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R
import com.psp.lookitup.databinding.FragmentDetailsBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel
import kotlinx.android.synthetic.main.fragment_details.*
import java.time.LocalDate
import java.time.Period


class DetailsFragment : Fragment() {
    val TAG = "Details Fragment"


    private val viewmodel: MainViewmodel by activityViewModels()
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

        val submitDetails = binding.btnSubmitRequest
        val name = binding.etName
        val email = binding.etEmail
        val date = binding.datePicker
        val gender = binding.rg1
        val occupation = binding.rg2
        val need = binding.rg3


        submitDetails.setOnClickListener {
            val Fname = name.text.toString()
            val emailID = email.text.toString()
            val Date = date.toString()
            val Gender = onGenderRadioButtonClicked(view)
            val Occupation = onOccupationRadioButtonClicked(view).toString()
            val Need = onstatusRadioButtonClicked(view).toString()

            val user = hashMapOf(
                "name" to Fname,
                "emailID" to emailID,
                "DOB" to Date,
                "gender" to Gender,
                "Status" to Need,
                "occupation" to Occupation,

            )
            dbref.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added ")

                }.addOnSuccessListener {
                    Toast.makeText(
                        requireContext(),
                        "Data Added to database successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    view.findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }





//        val user = hashMapOf(
//            "name" to "Prathamesh Karambelkar",
//            "emailId" to "pkarambelkar.2002@gmail.com",
//            "DOB" to "19",
//            "gender" to "Male",
//            "Status" to "Looking for room",
//            "occupation" to "student",
//            "location" to "Ambernath"
//        )
//        dbref.collection("User")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }

    }
    fun onGenderRadioButtonClicked(view: View): String {
        var gender = ""
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbMale ->
                    if (checked) {
                        gender = rbMale.text.toString()
                    }
                R.id.rbFemale ->
                    if (checked) {
                        gender = rbFemale.text.toString()
                    }
            }
        }

        return gender
    }

    fun onstatusRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbLookingForRoom ->
                    if (checked) {
                        val lookingforroom = rbLookingForRoom.text.toString()

                    }
                R.id.rbLookingForRoommates ->
                    if (checked) {
                        val lookingforroommates = rbLookingForRoommates.text.toString()
                    }
            }
        }
    }

    fun onOccupationRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked


            when (view.getId()) {
                R.id.rbStudent ->
                    if (checked) {
                        val student = rbStudent.text.toString()
                    }
                R.id.rbWorkingProf ->
                    if (checked) {
                        val working = rbWorkingProf.text.toString()
                    }
            }
        }
    }


}