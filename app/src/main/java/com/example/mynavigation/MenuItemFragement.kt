package com.example.mynavigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynavigation.databinding.FragementMenuItemBinding
import com.squareup.picasso.Picasso

class MenuItemFragement: Fragment() {
    private lateinit var binding: FragementMenuItemBinding
    private lateinit var myModel:MenuModel
    private lateinit var cartDatabaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragementMenuItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myModel = ViewModelProvider(requireActivity()).get(MenuModel::class.java)
        //Toast.makeText(requireActivity(),myModel.data.toString(),Toast.LENGTH_SHORT).show()
        val  menuItem = myModel.data.first()
        binding.foodName.text = menuItem.name
        binding.price.text = menuItem.price.toString()
        val ingredients = menuItem.ingredients.replace(",", "\n")
        binding.description.text = ingredients

        Picasso.get().load(menuItem.image).into(binding.imageView32)


        val button = view.findViewById<Button>(R.id.button)

        // Initialize your CartDatabaseHelper
        cartDatabaseHelper = DatabaseHelper(requireContext())

        // Check if the user is logged in
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.contains("username") // Check if username exists in SharedPreferences

        // Set up the button click listener
        button.setOnClickListener {
            if (isLoggedIn) {
                // Create a new CartItem object
                val newItem = CartItem(
                    id = menuItem.id, // Set the ID as needed
                    name = menuItem.name,
                    price = menuItem.price, // Set the price as needed
                    image = menuItem.image,
                    ingredients= menuItem.ingredients ,
                    restaurantId = menuItem.restaurantId,
                    quantity = 1 // Set the quantity as needed
                )

                // Add the new item to the table
                cartDatabaseHelper.addToCart(newItem)

                Toast.makeText(requireContext(), "Item added to cart", Toast.LENGTH_SHORT).show()
            } else {
                // User is not logged in, navigate to the login fragment
                findNavController().navigate(R.id.action_menuItemFragement_to_login)
            }

        }

    }
}