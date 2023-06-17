package com.example.mynavigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.databinding.ShoppingCardBinding

class CartAdapter(private val context: Context, private var cartList : List<Menu>) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    var onItemClick: ((Unit) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        return CartAdapter.MyViewHolder(
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
        holder.binding.apply {
            textView.text = cartList[position].name
            textView6.text = cartList[position].nbItems.toString()
        }

        holder.binding.btnPlus.setOnClickListener(){
            try {
                val currentValue = cartList[position].nbItems
                val newValue = currentValue + 1
                cartList[position].nbItems = newValue
                holder.binding.textView6.text = newValue.toString()
            }
            catch (e: Exception){
                Toast.makeText(context , e.message , Toast.LENGTH_SHORT).show()
            }
        }
    }
}