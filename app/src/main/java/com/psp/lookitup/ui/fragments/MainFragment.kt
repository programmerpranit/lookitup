package com.psp.lookitup.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.psp.lookitup.R
import com.psp.lookitup.data.Request
import com.psp.lookitup.databinding.FragmentMainBinding
import com.psp.lookitup.ui.adapters.RequestAdapter
import com.psp.lookitup.ui.viewmodels.MainViewmodel


class MainFragment : Fragment(), RequestAdapter.IRequestClicked {

    val TAG = "Main Fragment"
    val dbref = Firebase.firestore
    private val viewmodel: MainViewmodel by activityViewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RequestAdapter(this)
        viewmodel.getRequests()

        viewmodel.requests.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        binding.fab.setOnClickListener{
            view.findNavController().navigate(R.id.action_mainFragment_to_addRequestFragment)
        }

        val recyclerView = binding.rvMainFrag
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }







    override fun onItemClicked(item: Request) {
        val bundle = bundleOf("id" to item.id)
        binding.root.findNavController().navigate(R.id.action_mainFragment_to_requestDetailsFragment, bundle)

    }


}