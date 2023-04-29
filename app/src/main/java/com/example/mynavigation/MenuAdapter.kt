package com.example.mynavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.mynavigation.databinding.MenuLayoutBinding
import com.example.mynavigation.databinding.RestaurantLayoutBinding

class MenuAdapter(private val context: Context , private val menuList : List<Menu>) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
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
        }
    }
}