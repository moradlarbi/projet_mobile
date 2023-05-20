package com.example.mynavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.databinding.ShoppingCardBinding

class CartAdapter(private val context: Context, private val cartList : List<Menu>) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
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
        }

    }
}