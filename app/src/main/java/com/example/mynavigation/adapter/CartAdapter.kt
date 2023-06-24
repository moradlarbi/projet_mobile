package com.example.mynavigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.models.CartItem
import com.example.mynavigation.Utils.DatabaseHelper
import com.example.mynavigation.views.Mycard
import com.example.mynavigation.databinding.ShoppingCardBinding
import com.squareup.picasso.Picasso

class CartAdapter(private val context: Context, private var cartList : List<CartItem>, private val fragment: Mycard,) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    var onItemClick: ((Unit) -> Unit)? = null
    private lateinit var cartDatabaseHelper: DatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ShoppingCardBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount() = cartList.size




    class MyViewHolder(val binding: ShoppingCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        cartDatabaseHelper = DatabaseHelper(context)

        fun updateValue(toAdd :Int , position: Int){
            try {
                val currentItem = cartList[position]
                val updatedQuantity = currentItem.quantity + toAdd

                cartDatabaseHelper.updateQuantityInDatabase(currentItem.id, updatedQuantity)

                val updatedItem = currentItem.copy(quantity = updatedQuantity)
                cartList = cartList.toMutableList().apply { set(position, updatedItem) }


                holder.binding.textView6.text = updatedQuantity.toString()

                // Update cart summary in the fragment
                fragment.updateCartSummary()
            }
            catch (e: Exception){
                Toast.makeText(context , e.message , Toast.LENGTH_SHORT).show()
            }
        }

        holder.binding.apply {
            textView.text = cartList[position].name
            textView6.text = cartList[position].quantity.toString()
            Picasso.get().load(cartList[position].image).into(imageView6)
        }

        holder.binding.btnPlus.setOnClickListener(){
           updateValue(1,position)
        }

        holder.binding.btnMinus.setOnClickListener(){
          updateValue(-1 , position)
        }

        holder.binding.deleteItem.setOnClickListener {

            val itemId = cartList[position].id
            cartDatabaseHelper.deleteItemFromDatabase(itemId)
            cartList = cartList.filter { it.id != itemId } // Remove the item from the cartList
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartList.size)
        }


    }



}