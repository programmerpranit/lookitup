package com.psp.lookitup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.psp.lookitup.R
import com.psp.lookitup.databinding.ActivityMainBinding
import com.psp.lookitup.databinding.FragmentDetailsBinding
import com.psp.lookitup.databinding.FragmentVerifyBinding


class DetailsFragment : Fragment() {
    lateinit var spinner1: Spinner
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
        var options1= arrayOf("Male","Female")
        spinner1.adapter= ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,options1)


        binding.spinner1.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }
        }

    }
}