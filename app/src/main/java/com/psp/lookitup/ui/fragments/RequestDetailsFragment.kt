package com.psp.lookitup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.psp.lookitup.R
import com.psp.lookitup.databinding.FragmentAddRequestBinding
import com.psp.lookitup.databinding.FragmentRequestDetailsBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestDetailsFragment : Fragment(R.layout.fragment_request_details) {

    private val viewmodel: MainViewmodel by activityViewModels()
    private lateinit var binding: FragmentRequestDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("amount")

        if(id != null) {
            viewmodel.getFullRequest(id)
        }
    }

}