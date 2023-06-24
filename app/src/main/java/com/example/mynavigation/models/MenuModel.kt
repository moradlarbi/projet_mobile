package com.example.mynavigation.models

import androidx.lifecycle.ViewModel
import com.example.mynavigation.entity.MenuItem

class MenuModel:ViewModel() {
    val data = mutableListOf<MenuItem>()
}



