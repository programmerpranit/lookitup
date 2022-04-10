package com.psp.lookitup.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
//    var email = ""

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
        val btnletsConnece = binding.btnLetsConnect
        val id = arguments?.getString("id")
        if (id != null) {
            viewmodel.getFullRequest(id)
        }
        viewmodel.requestDetails.observe(viewLifecycleOwner) { req ->
            binding.tvNameFullRequest.text = req.name
            binding.tvEmailIdFullRequest.text = req.emailId
            binding.tvReqTitle.text = req.requestTitle
            binding.tvdescriptionfullrequest.text = req.Description
            binding.textView8.text = req.roomLocation
            binding.textView3.text = req.DOB
            binding.textView6.text = req.gender
            binding.textView9.text = req.Status
            binding.textView10.text = req.occupation
            val email = req.emailId.toString()
//
        }
        btnletsConnece.setOnClickListener {
//            sendEmail(email)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "email")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding your request on Lookitup")
            startActivity(Intent.createChooser(intent, "Send Email"))
        }
    }
//    fun composeEmail(addresses: Array<String?>?, subject: String?) {
//        val intent = Intent(Intent.ACTION_SENDTO)
//        intent.data = Uri.parse("mailto:") // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent)
//        }
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








