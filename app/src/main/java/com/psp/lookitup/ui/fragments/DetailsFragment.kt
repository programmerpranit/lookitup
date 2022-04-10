package com.psp.lookitup.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R
import com.psp.lookitup.data.User
import com.psp.lookitup.databinding.FragmentDetailsBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {
    val TAG = "Details Fragment"

    private val viewmodel: MainViewmodel by activityViewModels()

    val dbref = Firebase.firestore

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    val auth = FirebaseAuth.getInstance()
    val uid = auth.currentUser?.uid

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
        val date = binding.etAge


        submitDetails.setOnClickListener {
            val Fname = name.text.toString()
            val emailID = email.text.toString()
            val Date = date.text.toString()
            val Gender = onGenderRadioButtonClicked(view)
            val Occupation = onOccupationRadioButtonClicked(view)
            val Need = onstatusRadioButtonClicked(view)


            val user = User(
                id = uid?: "",
                name = Fname,
                emailId = emailID,
                gender = Gender,
                Status = Need,
                occupation = Occupation
            )

            if (uid != null) {
                dbref.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added ")
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
        }


    }
    fun onGenderRadioButtonClicked(view: View): String {

        // Is the button now checked?
        val checked = binding.rg1

        val cid = checked.checkedRadioButtonId

        val gender =  if(cid == binding.rbMale.id){
            "Male"
        } else {
            "Female"
        }

        return gender
    }

    fun onstatusRadioButtonClicked(view: View): String {
        val cid = binding.rg2.checkedRadioButtonId

        val status = if(cid == binding.rbLookingForRoom.id){
            "Looking For Room"
        }  else {
            "Looking For Roommate"
        }
        return status
    }

    fun onOccupationRadioButtonClicked(view: View): String {
        val cid = binding.rg3.checkedRadioButtonId

        val occupation = if(cid == binding.rbStudent.id){
            "Student"
        }  else {
            "Working Professional"
        }
        return occupation
    }

}