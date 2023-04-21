package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.mynavigation.databinding.Fragment1Binding
import com.example.mynavigation.databinding.Fragment2Binding


class Fragment2 : Fragment() {

    private lateinit var binding: Fragment2Binding
    lateinit var myModel: MyModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment2Binding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myModel = ViewModelProvider(requireActivity()).get(MyModel::class.java)
        Toast.makeText(requireActivity(),myModel.data.toString(),Toast.LENGTH_SHORT).show()
    }
}