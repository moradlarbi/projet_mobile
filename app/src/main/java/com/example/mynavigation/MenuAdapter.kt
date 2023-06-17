package com.example.mynavigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.mynavigation.databinding.MenuLayoutBinding
import com.example.mynavigation.databinding.RestaurantLayoutBinding
import com.squareup.picasso.Picasso

class MenuAdapter(private val context: Context , private val menuList : List<MenuItem>) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    var onItemClick: ((Unit) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MyViewHolder {
        return MenuAdapter.MyViewHolder(
            MenuLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount() = menuList.size




    class MyViewHolder(val binding: MenuLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            textView16.text = menuList[position].name
            textView18.text = menuList[position].name
            textView20.text = menuList[position].price.toString()
            Picasso.get().load(menuList[position].image).into(imageView18)
        }
        holder.binding.addBtn.setOnClickListener(){
            it.findNavController().navigate(R.id.action_fragment2_to_menuItemFragement)
        }
    }
}