package com.psp.lookitup.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
class RequestDetailsFragment : Fragment(R.layout.fragment_request_details) {

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
            binding.tvNameFullRequest.text = req.requestTitle
            email = req.emailId
//            binding.tvLocationFullRequest.text = req.roomLocation
        }
//        binding.btnSendEmail.setOnClickListener {
//        }

        fun sendEmail(recipient: String) {
            /*ACTION_SEND action to launch an email client installed on your Android device.*/
            val mIntent = Intent(Intent.ACTION_SEND)
            /*To send an email you need to specify mailto: as URI using setData() method
            and data type will be to text/plain using setType() method*/
            mIntent.data = Uri.parse("mailto:$email")
            mIntent.type = "text/plain"
            // put recipient email in intent
            /* recipient is put as array because you may wanna send email to multiple emails
               so enter comma(,) separated emails, it will be stored in array*/
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))




            try {
                //start email intent
                startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
            }
            catch (e: Exception){
                //if any thing goes wrong for example no email client application or any exception
                //get and show exception message

            }

        }
    }


}




