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
import com.psp.lookitup.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    lateinit var spinner1: Spinner
    lateinit var sp1t: TextView

    lateinit var spinner2: Spinner
    lateinit var sp2t: TextView

    lateinit var spinner3: Spinner
    lateinit var sp3t: TextView

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

        //Spinner 1 logic
        spinner1 = binding.root.findViewById(R.id.spinner1)
        sp1t = binding.root.findViewById(R.id.dst1)
        val options1= arrayOf("Male","Female")
        spinner1.adapter= ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,options1)
        spinner1.onItemSelectedListener=object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                sp1t.text = "Nothing Selected"
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                sp1t.text = options1.get(p2)
            }

        }//sp1 ends

//        //Spinner 2 logic
//        spinner2 = binding.root.findViewById(R.id.spinner2)
//        sp2t = binding.root.findViewById(R.id.dst2)
//        val options2= arrayOf("Student","Working")
//        spinner2.adapter= ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,options1)
//        spinner2.onItemSelectedListener=object: AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                sp2t.text = "Nothing Selected"
//            }
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                sp2t.text = options2.get(p2)
//            }
//
//        }//sp2 ends
//
//        //Spinner 3 logic
//        spinner3 = binding.root.findViewById(R.id.spinner3)
//        sp3t = binding.root.findViewById(R.id.dst3)
//        val options 3= arrayOf("Male","Female")
//        spinner3.adapter= ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,options3)
//        spinner3.onItemSelectedListener=object: AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                sp3t.text = "Nothing Selected"
//            }
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                sp3t.text = options3.get(p2)
//            }
//
//        }//sp3 ends

    }
}