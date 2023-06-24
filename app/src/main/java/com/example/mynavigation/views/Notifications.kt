package com.example.mynavigation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynavigation.entity.Notification
import com.example.mynavigation.adapter.NotificationsAdapter
import com.example.mynavigation.databinding.FragmentNotificationsBinding

class Notifications : Fragment() {
    private lateinit var binding: FragmentNotificationsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationsBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = NotificationsAdapter(loadData(),requireActivity()).apply{}

    }
    fun loadData():List<Notification> {
        val data = mutableListOf<Notification>()

        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))
        data.add(Notification("Papa Pizza","Tofit description Notif description notifi description Tofit description Notif description notifi description","indien"))





        return data
    }


}