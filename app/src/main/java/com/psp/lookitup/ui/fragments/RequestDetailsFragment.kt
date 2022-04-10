package com.psp.lookitup.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R
import com.psp.lookitup.databinding.FragmentFullRequestBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_full_request.*

@AndroidEntryPoint
class RequestDetailsFragment : Fragment() {

    val dbref = Firebase.firestore

    private val viewmodel: MainViewmodel by activityViewModels()
    private lateinit var binding: FragmentFullRequestBinding
    private lateinit var email:String

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
            Log.d(TAG, req.emailId)
            email = req.emailId
            binding.tvdescriptionfullrequest.text = req.Description
            binding.tvReqAge.text = req.DOB
            binding.tvReqGender.text = req.gender
            binding.tvReqLocation.text = req.roomLocation
            binding.tvReqStatus.text = req.Status
            binding.tvReqOccupation.text = req.occupation
        }
        binding.btnLetsConnect.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
//            intent.setType("message/rfc822")
            intent.putExtra(Intent.EXTRA_EMAIL, "mailto:$email")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding your request on Lookitup")
            startActivity(Intent.createChooser(intent, "Send Email"))
        }


    }



}




