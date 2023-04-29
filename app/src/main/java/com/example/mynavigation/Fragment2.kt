package com.example.mynavigation

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mynavigation.databinding.Fragment2Binding


class Fragment2 : Fragment() {

    private lateinit var binding: Fragment2Binding
    lateinit var myModel: MyModel
    lateinit var menuModel: MenuModel
    lateinit var myAdapter: MenuAdapter


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
        //Toast.makeText(requireActivity(),myModel.data.toString(),Toast.LENGTH_SHORT).show()
        val  restaurant = myModel.data.first()
        binding.textView10.text = restaurant.name

        myAdapter = MenuAdapter(requireContext() , loadData())
        val viewPager2 = binding.menuSwiper
        viewPager2.adapter = myAdapter
    }

    fun loadData():List<Menu>{
        val menuData = mutableListOf<Menu>()
        menuData.add(Menu("Ham"))
        menuData.add(Menu("Ham"))
        menuData.add(Menu("Ham"))
        return menuData
    }}