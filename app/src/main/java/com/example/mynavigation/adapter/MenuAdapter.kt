package com.example.mynavigation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.entity.MenuItem
import com.example.mynavigation.models.MenuModel
import com.example.mynavigation.R
import com.example.mynavigation.databinding.MenuLayoutBinding
import com.squareup.picasso.Picasso

class MenuAdapter(private val context: ViewModelStoreOwner, private val menuList : List<MenuItem>) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    var onItemClick: ((Unit) -> Unit)? = null
    lateinit var myModel: MenuModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
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
            myModel = ViewModelProvider(context).get(MenuModel::class.java)
            myModel.data.add(0 ,menuList[position] )
            it.findNavController().navigate(R.id.action_fragment2_to_menuItemFragement)
        }
    }
}