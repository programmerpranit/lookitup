package com.psp.lookitup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.psp.lookitup.R
import com.psp.lookitup.data.Request
import com.psp.lookitup.data.User
import com.psp.lookitup.databinding.FragmentAddRequestBinding
import com.psp.lookitup.ui.viewmodels.MainViewmodel


class AddRequestFragment : Fragment() {

    val TAG = "Add Request Fragment"
    private val viewmodel: MainViewmodel by activityViewModels()
    private lateinit var binding: FragmentAddRequestBinding

   val auth = FirebaseAuth.getInstance()


    val uid = auth.currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       // viewmodel.getUserById(uid!!)

        var user = User()

        viewmodel.fullUser.observe(viewLifecycleOwner){
            user = it
        }


        binding.btnSubmitRequest.setOnClickListener{
            val request = Request()
            request.requestTitle = binding.etRequestTitle.text.toString()
            request.Description = binding.etDesc.text.toString()
            request.roomLocation = binding.etLocation.text.toString().lowercase()
            request.name = user.name
            request.emailId = user.emailId
            request.occupation = user.occupation
            request.gender = user.gender
            request.DOB =user.DOB

            viewmodel.addRequest(request)
            Toast.makeText(requireContext(), "Your Request Added Successfully", Toast.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_addRequestFragment_to_mainFragment)
        }

    }

}