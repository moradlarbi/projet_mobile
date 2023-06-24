package com.example.mynavigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.entity.Notification
import com.example.mynavigation.models.Restaurant
import com.example.mynavigation.databinding.NotificationLayoutBinding

public class NotificationsAdapter(val data:List<Notification>, val ctxt: Context): RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>() {
    var onItemClick: ((Restaurant) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(NotificationLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {

            notifTitle.text = data[position].title
            notifDescription.text = data[position].description
            notfiDate.text = (data[position].date)
        }

    }


    class MyViewHolder(val binding: NotificationLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}