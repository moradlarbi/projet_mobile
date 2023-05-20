package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.recyclerView.adapter = CartAdapter(requireActivity() ,loadData()).apply{}

    }
    fun loadData():List<Menu> {
        val data = mutableListOf<Menu>()

        data.add(Menu("Menu 1"))
        data.add(Menu("Menu 2"))
        data.add(Menu("Menu 3"))
        data.add(Menu("Menu 1"))
        data.add(Menu("Menu 2"))
        data.add(Menu("Menu 3"))
        data.add(Menu("Menu 1"))
        data.add(Menu("Menu 2"))
        data.add(Menu("Menu 3"))



        return data
    }

}