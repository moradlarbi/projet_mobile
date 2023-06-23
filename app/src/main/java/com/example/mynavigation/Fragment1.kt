package com.example.mynavigation

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.databinding.Fragment1Binding
import com.example.mynavigation.retrofit.Endpoint
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Fragment1 : Fragment() {

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

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
        binding.recyclerView.adapter = RestaurantAdapter(loadData(),requireActivity()).apply {
            onItemClick = { restaurant ->
                // Handle item click and pass data to Fragment2
                // Set the data in MyModel
                // Always add it at position 0
                print(restaurant)
                myModel.data.add(0, restaurant)

                // Check if the current destination is Fragment1 before navigating to Fragment2
                val currentDestination = findNavController().currentDestination?.id
                if (currentDestination == R.id.fragment1) {
                    findNavController().navigate(R.id.action_fragment1_to_fragment2)
                }
            } }




        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        val layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = layoutManager
        loadRestaurants() ;
        /*binding.button.setOnClickListener {
            myModel.data.add("test")
            view.findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)!!
        bottomNavigation.setVisibility(View.VISIBLE)
        // Set up the bottom navigation view
    }


    fun loadData():List<Restaurant> {
        val data = mutableListOf<Restaurant>()
      /*  data.add(Restaurant("Papa Pizza",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Mama Pizza",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 3",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 4",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 5",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 6",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 7",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
        data.add(Restaurant("Movie 8",R.drawable.restaurant,"geo:36,3?q=Alger","indien", 4.5F,"0555125555","morad@esi.dz","fb://page/218641444910278","https://www.instagram.com/lamsaty.oran/","fb://page/218641444910278","https://www.facebook.com/Restaurant.Arabesque2016"))
*/
        return data
    }

    fun loadRestaurants() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est  ", Toast.LENGTH_SHORT).show()
            }
        }

       progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
            try {
                val response = Endpoint.createEndpoint().getAllRestaurants()

                if (response.isSuccessful && response.body() != null) {
                    val movieResponse = response.body()!!

                    recyclerView.adapter = RestaurantAdapter( movieResponse.toMutableList() ,requireActivity()).apply {
                        onItemClick = { restaurant ->
                            // Handle item click and pass data to Fragment2
                            // Set the data in MyModel
                            //always add it in position 0
                            print(restaurant) ;
                            myModel.data.add(0,restaurant)

                            findNavController().navigate(R.id.action_fragment1_to_fragment2)
                        } }
                    progressBar.visibility = View.INVISIBLE
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                progressBar.visibility = View.INVISIBLE
                val errorMessage = "Une erreur s'est produite: ${e.message}"
                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}