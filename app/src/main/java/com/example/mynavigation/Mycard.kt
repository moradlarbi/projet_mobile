package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynavigation.databinding.FragmentMycardBinding
import com.example.mynavigation.databinding.FragmentNotificationsBinding

class Mycard : Fragment() {
    private lateinit var binding: FragmentMycardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMycardBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = CartAdapter(requireActivity() ,loadData()).apply {
            onItemClick = { restaurant ->
                // Handle item click and pass data to Fragment2
                // Set the data in MyModel
                //always add it in position 0
                Toast.makeText(context , "de" , Toast.LENGTH_SHORT).show()
            } }

    }
    fun loadData(): List<Menu> {

        val data = mutableListOf<Menu>()

        data.add(Menu("Menu 1" , 5 ))
        data.add(Menu("Menu 1" , 5 ))
        data.add(Menu("Menu 1" , 5 ))
        data.add(Menu("Menu 1" , 5 ))

        return data
    }

}