package com.psp.lookitup.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.psp.lookitup.R
import com.psp.lookitup.databinding.FragmentAddRequestBinding
import com.psp.lookitup.databinding.FragmentFullRequestBinding
import com.psp.lookitup.databinding.FragmentRequestDetailsBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestDetailsFragment : Fragment(R.layout.fragment_request_details) {

    private val viewmodel: MainViewmodel by activityViewModels()
    private lateinit var binding: FragmentFullRequestBinding

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

        val id = arguments?.getString("id")


        if(id != null) {
            viewmodel.getFullRequest(id)
        }



        viewmodel.requestDetails.observe(viewLifecycleOwner){ req ->

            binding.tvNameFullRequest.text = req.requestTitle
            binding.tvLocationFullRequest.text = req.roomLocation
        }
    }

}