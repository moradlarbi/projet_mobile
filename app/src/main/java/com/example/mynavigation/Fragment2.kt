package com.example.mynavigation

import android.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mynavigation.databinding.Fragment2Binding
import com.example.mynavigation.retrofit.Endpoint
import com.example.mynavigation.retrofit.rateData
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Fragment2 : Fragment() {

    private lateinit var binding: Fragment2Binding
    lateinit var myModel: MyModel
    lateinit var menuModel: MenuModel
    lateinit var myAdapter: MenuAdapter
    lateinit var progressBar: ProgressBar


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
        binding.textView.text = restaurant.name
        binding.cuisinType.text = restaurant.CuisineType
        Picasso.get().load(restaurant.logo).into(binding.restLogo)

        progressBar = view.findViewById(com.example.mynavigation.R.id.progressBar2) as ProgressBar
        binding.phoneIcon.setOnClickListener(){
            try {
                val data = Uri.parse("tel:${restaurant.phone}")
                val intent = Intent(Intent.ACTION_DIAL, data)
                requireContext().startActivity(intent)
            }
            catch (e: Exception){
                print(e.message)
            }
        }

        binding.facebookIcon.setOnClickListener(){
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.fcb))
                requireContext().startActivity(intent)
            }
            catch (e: Exception){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.fcb))
                requireContext().startActivity((intent))
            }
        }

        binding.mapIcon.setOnClickListener(){
            try {
                val latitude = restaurant.latitude
                val longitude = restaurant.longitude
                val geoUri = Uri.parse("geo:$latitude,$longitude")
                val intent = Intent(Intent.ACTION_VIEW, geoUri)
                requireContext().startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.instaIcon.setOnClickListener(){
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.instagram))
                requireContext().startActivity(intent)

            }
            catch (e: Exception){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.instagram))
                requireContext().startActivity((intent))
            }
        }

        binding.googleIcon.setOnClickListener(){
            try {
                val data = Uri.parse("mailto:${restaurant.email}")
                val intent = Intent(Intent.ACTION_SENDTO, data)
                requireContext().startActivity(intent)
            }
            catch (e: Exception){
                print(e.message)
            }
        }


        loadMenuItems();
        val ratingBar = binding.ratingBar2

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            // Convert the rating to an integer
            val ratingValue = rating.toInt()

            // Get the user ID from SharedPreferences
            val sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            val userId = sharedPreferences.getInt("id", -1)

            // Check if the user is logged in
            if (userId == -1) {
                // User is not logged in, redirect to the login


            }

            // Get the restaurant ID from the selected restaurant
            val restaurantId = restaurant.id

            // Create the rateData object with the obtained values
            val rate = rateData(ratingValue, userId, restaurantId)

            val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                requireActivity().runOnUiThread {
                    Toast.makeText(requireActivity(), "Une erreur s'est  ", Toast.LENGTH_SHORT).show()
                }
            }

            CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
                try {
                    val response = Endpoint.createEndpoint().rateRestaurant(rate)

                    // Handle the response
                    if (response.isSuccessful && response.body() != null) {
                        val responseRate = response.body()!!

                        // Show a success toast
                        Toast.makeText(requireContext(), "Rating Done", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle unsuccessful sign-up
                        // Show an error toast
                        Toast.makeText(requireContext(), "Rating Failed", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }



    fun loadData():List<Menu>{
        val menuData = mutableListOf<Menu>()
        menuData.add(Menu("Ham" , 5))
        menuData.add(Menu("Ham2" , 5))
        return menuData
    }

    fun loadMenuItems(){
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est  ", Toast.LENGTH_SHORT).show()
            }
        }

        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
            try {
                val response = Endpoint.createEndpoint().getResaturantMenu(myModel.data[0].id)

                if (response.isSuccessful && response.body() != null) {
                    val movieResponse = response.body()!!
                    val viewPager2 = binding.menuSwiper
                    viewPager2.adapter =  MenuAdapter(requireActivity(), movieResponse.toMutableList() )
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