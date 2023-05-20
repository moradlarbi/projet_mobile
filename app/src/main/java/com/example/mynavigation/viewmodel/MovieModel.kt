package com.example.mynavigation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mynavigation.entity.Movie

class MovieModel:ViewModel() {
    var data = mutableListOf<Movie>()
}