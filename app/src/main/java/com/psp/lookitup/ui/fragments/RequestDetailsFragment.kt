package com.psp.lookitup.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R
import com.psp.lookitup.databinding.FragmentFullRequestBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestDetailsFragment : Fragment(R.layout.fragment_request_details) {

    val dbref = Firebase.firestore

    private val viewmodel: MainViewmodel by activityViewModels()
    private lateinit var binding: FragmentFullRequestBinding
    var email = ""

    val TAG = "reqdetails"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbref = Firebase.firestore
        val id = arguments?.getString("id")
        if (id != null) {
            viewmodel.getFullRequest(id)
        }

        viewmodel.requestDetails.observe(viewLifecycleOwner) { req ->
            binding.tvNameFullRequest.text = req.name
            binding.tvEmailIdFullRequest.text = req.emailId
            binding.tvReqTitle.text = req.requestTitle
            binding.tvdescriptionfullrequest.text = req.Description
            binding.tvReqLocation.text = req.roomLocation
            binding.tvReqAge.text = req.DOB
            binding.tvReqGender.text = req.gender
            binding.tvReqStatus.text = req.Status
            binding.tvReqOccupation.text = req.occupation
            email = req.emailId
//
        }
        binding.btnLetsConnect.setOnClickListener {
//            sendEmail(email)
        }
    }


}

//fun sendEmail(recipient: String) {
//    val mIntent = Intent(Intent.ACTION_SEND)
//    mIntent.data = Uri.parse("mailto:$email")
//    mIntent.type = "text/plain"
//
//    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
//    try {
//        startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
//    } catch (e: Exception) {
//    }








