package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mynavigation.R
import com.example.mynavigation.databinding.Fragment1Binding

class Fragment1 : Fragment() {

    private lateinit var binding: Fragment1Binding
    lateinit var myModel: MyModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = Fragment1Binding.inflate(layoutInflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myModel = ViewModelProvider(requireActivity()).get(MyModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = RestaurantAdapter(loadData(),requireActivity())

        /*binding.button.setOnClickListener {
                myModel.data.add("test")
                view.findNavController().navigate(R.id.action_fragment1_to_fragment2)
            }*/
    }
    fun loadData():List<Restaurant> {
        val data = mutableListOf<Restaurant>()
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 1",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))

        return data
    }
}