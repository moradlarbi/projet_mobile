package com.example.mynavigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.R
import com.example.mynavigation.retrofit.OrderResponse

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val orders: MutableList<OrderResponse> = mutableListOf()

    fun setOrders(orders: List<OrderResponse>) {
        this.orders.clear()
        this.orders.addAll(orders)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val orderIdTextView: TextView = itemView.findViewById(R.id.orderIdTextView)
        private val noteTextView: TextView = itemView.findViewById(R.id.noteTextView)
        private val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(order: OrderResponse) {
            orderIdTextView.text = order.id.toString()
            noteTextView.text = order.note
            locationTextView.text = order.location
            statusTextView.text = order.status
        }
    }
}
