package com.example.mynavigation.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynavigation.R
import com.example.mynavigation.Utils.DatabaseHelper
import com.example.mynavigation.models.MyModel
import com.example.mynavigation.retrofit.Endpoint
import com.example.mynavigation.retrofit.OrderData
import com.example.mynavigation.retrofit.OrderItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderNotes : Fragment() {

    private lateinit var localisationEditText: EditText
    private lateinit var orderNotesEditText: EditText
    private lateinit var addOrderButton: Button
    private lateinit var cartDatabaseHelper: DatabaseHelper
    lateinit var myModel: MyModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_notes, container, false)
        localisationEditText = view.findViewById(R.id.localisation)
        orderNotesEditText = view.findViewById(R.id.orderNotesInput)
        addOrderButton = view.findViewById(R.id.button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartDatabaseHelper = DatabaseHelper(requireContext())
        myModel = ViewModelProvider(requireActivity()).get(MyModel::class.java)
        addOrderButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
                val userId = sharedPreferences.getInt("id", -1)
                val location = localisationEditText.text.toString()
                val notes = orderNotesEditText.text.toString()
                val restaurantID = myModel.data.first().id

                val cartItems = cartDatabaseHelper.getCartItems()
                val orderItems = cartItems.map { cartItem ->
                    OrderItem(cartItem.id, cartItem.quantity)
                }

                val orderData = OrderData(userId, orderItems, location, restaurantID ,notes) // Replace 1 with the actual restaurant ID

                try {
                    val response = Endpoint.createEndpoint().addOrder(orderData)
                    if (response.isSuccessful && response.body() != null) {
                        val responseData = response.body()!!
                        // Handle the successful response
                        findNavController().navigate(R.id.action_orderNote_to_OrderList)
                    } else {
                        // Handle the error response
                    }
                } catch (e: Exception) {
                    // Handle the exception
                }
            }
        }
    }
}
