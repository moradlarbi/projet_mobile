package com.example.mynavigation

import android.content.Context
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
    private lateinit var cartDatabaseHelper: DatabaseHelper

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

        cartDatabaseHelper = DatabaseHelper(requireContext())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = CartAdapter(requireActivity() ,loadData() , this@Mycard).apply {
            onItemClick = { restaurant ->
                // Handle item click and pass data to Fragment2
                // Set the data in MyModel
                //always add it in position 0
                Toast.makeText(context , "de" , Toast.LENGTH_SHORT).show()
            } }
        // Calculate total price and number of items
        val cartItems = loadData()
        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        val numberOfItems = cartItems.sumOf { it.quantity }

        // Set the values in the corresponding TextViews
        binding.totalPrice.text = "${totalPrice} DA"
        binding.nbItems.text = "${numberOfItems} Items"

        // Handle buy button click
        binding.buyButton.setOnClickListener {
            if (isLoggedIn()) {
                findNavController().navigate(R.id.action_mycard_to_orderNotes)
            } else {
                // User is not logged in, navigate to the login page
                findNavController().navigate(R.id.action_mycard_to_login)
            }
        }

    }

    private fun isLoggedIn(): Boolean {
        // Check if the user is logged in
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.contains("username")
        return isLoggedIn
    }

    fun loadData(): List<CartItem> {

        // Retrieve menu items from the database
        val cartItems = cartDatabaseHelper.getCartItems()

        return cartItems
    }

     fun updateCartSummary() {

        val cartItems = loadData()
        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        val numberOfItems = cartItems.sumOf { it.quantity }

        binding.totalPrice.text = "${totalPrice} DA"
        binding.nbItems.text = "${numberOfItems} Items"
    }

}