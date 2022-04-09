package com.psp.lookitup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.psp.lookitup.R
import com.psp.lookitup.databinding.ActivityMainBinding
import com.psp.lookitup.databinding.FragmentDetailsBinding
import com.psp.lookitup.databinding.FragmentVerifyBinding


class DetailsFragment : Fragment() {
    lateinit var spinner1: Spinner
    lateinit var tvMF: TextView
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

        spinner1 = binding.root.findViewById(R.id.spinner1)
        tvMF = binding.root.findViewById(R.id.tvG)
        val options1= arrayOf("Male","Female")

        spinner1.adapter= ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,options1)

        spinner1.onItemSelectedListener=object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                tvMF.text = "Nothing Selected"
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                 tvMF.text = options1.get(p2)
            }

        }

    }
}